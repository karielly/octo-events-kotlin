package issueevent.repository

import issueevent.dto.Event
import issueevent.dto.Issue
import issueevent.model.EventEntity
import issueevent.model.EventTable
import issueevent.model.IssueEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

interface EventRepository {
    fun save(event: Event): Event
    fun getEventsByIssue(id: Int): List<Event>
}

class EventRepositoryImp : EventRepository {
    override fun getEventsByIssue(id: Int) = transaction {
        EventEntity.find { EventTable.issueId eq id }.map { Event(
            it.action,
            Issue(
                it.issue.id.value,
                it.issue.title,
                it.issue.state,
                it.issue.createdAt.toString(),
                it.issue.updated.toString()
            )) }
    }

    override fun save(event: Event) = transaction {
        val issue = IssueEntity.findById(event.issue.id)?.let {
            it.apply {
                this.updated = DateTime(event.issue.updated)
            }
        }?: IssueEntity.new(event.issue.id) {
            this.state = event.issue.state
            this.title = event.issue.title
            this.createdAt = DateTime(event.issue.createdAt)
            this.updated = DateTime(event.issue.updated)
        }

        val eventEntity = EventEntity.new {
            this.action = event.action
            this.issue = issue
            this.createdAt = DateTime.now()
        }
        Event(
            eventEntity.action,
            Issue(
                issue.id.value,
                issue.title,
                issue.state,
                issue.createdAt.toString(),
                issue.updated.toString()
            ))
    }

}
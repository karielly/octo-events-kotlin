package issueevent.repository

import issueevent.dto.Event
import issueevent.dto.Issue
import issueevent.model.EventEntity
import issueevent.model.EventTable
import issueevent.model.IssueEntity
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

interface EventRepository {
    fun save(event: Event): Boolean
    fun getEventsByIssue(id: Int): List<Event>
}

class EventRepositoryImp : EventRepository {
    override fun getEventsByIssue(id: Int) = transaction {
        EventEntity.find { EventTable.issueId eq id }.map { Event(
            it.action,
            Issue(it.issue.id.value, it.issue.title, it.issue.state)) }
    }

    override fun save(event: Event) = transaction {
        val issue = IssueEntity.findById(event.issue.id)?: IssueEntity.new(event.issue.id) {
            this.state = event.issue.state
            this.title = event.issue.title
        }
        EventEntity.new {
            this.action = event.action
            this.issue = issue
            this.createdAt = DateTime.now()
        }
        true
    }

}
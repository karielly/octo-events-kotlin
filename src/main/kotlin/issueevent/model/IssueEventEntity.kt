package issueevent.model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class IssueEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<IssueEntity>(IssueTable)

    var title by IssueTable.title
    var state by IssueTable.state
}

class EventEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<EventEntity>(EventTable)

    var action by EventTable.action
    var createdAt by EventTable.createdAt
    var updated by EventTable.updateddAt
    var issue by IssueEntity referencedOn EventTable.issueId
}
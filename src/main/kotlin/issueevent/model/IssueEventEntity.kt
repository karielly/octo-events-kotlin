package issueevent.model

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class IssueEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<IssueEntity>(IssueTable)

    var title by IssueTable.title
    var state by IssueTable.state
    var createdAt by IssueTable.createdAt
    var updated by IssueTable.updatedAt
}

class EventEntity(id: EntityID<Int>) : IntEntity(id) {

    companion object : IntEntityClass<EventEntity>(EventTable)

    var action by EventTable.action
    var createdAt by EventTable.createdAt
    var issue by IssueEntity referencedOn EventTable.issueId
}
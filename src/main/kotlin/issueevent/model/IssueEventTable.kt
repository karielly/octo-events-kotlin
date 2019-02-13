package issueevent.model

import org.jetbrains.exposed.dao.IdTable
import org.jetbrains.exposed.dao.IntIdTable

object IssueTable : IdTable<Int>("public.issue") {
    override val id = integer("id").primaryKey().entityId()
    val title = varchar("title", 200)
    val state = varchar("state", 50)
    val createdAt = IssueTable.datetime("created_at")
    val updateddAt = IssueTable.datetime("updated_at")
}

object EventTable : IntIdTable("public.event") {
    val action = varchar("action", 50)
    val issueId = reference("fk_issue", IssueTable)
    val createdAt = datetime("created_at")
}
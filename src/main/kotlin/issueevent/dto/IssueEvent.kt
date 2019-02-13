package issueevent.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Event(
    @JsonProperty("action", required = true) val action: String,
    @JsonProperty("issue", required = true) val issue: Issue
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Issue(
    @JsonProperty("id", required = true) val id: Int,
    @JsonProperty("title", required = true) val title: String,
    @JsonProperty("state", required = true) val state : String,
    @JsonProperty("created_at", required = true) val createdAt: String,
    @JsonProperty("updated_at", required = true) val updated : String
)
package issueevent.api

import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.apibuilder.EndpointGroup
import issueevent.handler.EventHandler

class EventEndPoint : EndpointGroup {

    override fun addEndpoints() {
        post("/events", EventHandler::createIssue)

        path("/issues") {
            get("/:id/events", EventHandler::getEventByIssue)
        }
    }

}
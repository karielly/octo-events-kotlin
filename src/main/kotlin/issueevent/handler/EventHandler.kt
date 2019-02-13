package issueevent.handler

import io.javalin.Context
import issueevent.dto.Event
import issueevent.service.EventService
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

object EventHandler : KoinComponent {

    private val eventService by inject<EventService>()

    fun createIssue(ctx: Context) {
        try {
            val event = ctx.bodyAsClass(Event::class.java)
            eventService.save(event)
            ctx.status(201).json(mapOf("message" to "event saved"))
        } catch (e: Exception) {
            ctx.status(500).json(mapOf("message" to "There was an error saving the event"))
        }
    }

    fun getEventByIssue(ctx: Context) {
        try {
            val id = ctx.pathParam("id").toInt()
            val events = eventService.getEventByIssue(id)
            ctx.json(events)
        } catch (e: Exception) {
            ctx.status(500).json(mapOf("message" to "There was an error fetching the events"))
        }
    }

    fun helloWorld(ctx: Context) {
        ctx.json("Hello world")
    }
}
package issueevent.service

import issueevent.dto.Event
import issueevent.repository.EventRepository


class EventService(private val eventRepository: EventRepository) {
    fun save(event: Event) = eventRepository.save(event)

    fun getEventByIssue(id: Int) = eventRepository.getEventsByIssue(id)
}
package issueevent

import issueevent.api.EventEndPoint
import issueevent.repository.EventRepository
import issueevent.repository.EventRepositoryImp
import issueevent.service.EventService
import org.koin.dsl.module.module

val IssueEventModule = module {
    single { EventEndPoint() }
    single { EventService(get()) }
    single<EventRepository> { EventRepositoryImp() }
}
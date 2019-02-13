package event.service

import issueevent.IssueEventModule
import issueevent.dto.Event
import issueevent.dto.Issue
import issueevent.repository.EventRepositoryImp
import issueevent.service.EventService
import org.jetbrains.exposed.sql.Database
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.standalone.StandAloneContext
import org.koin.standalone.get
import org.koin.standalone.getProperty
import org.koin.test.KoinTest
import org.koin.test.declareMock
import java.util.*

class EventServiceTest : KoinTest {

    private lateinit var eventRequest: Event

    @BeforeEach
    fun setUp() {
        StandAloneContext.startKoin(listOf(IssueEventModule), extraProperties = Properties().apply {
            load(javaClass.getResourceAsStream(("/application.conf")))
        }.entries.associate {
            it.key.toString() to it.value.toString()
        })

        Database.connect(
            url = getProperty("exposed.database.url"),
            driver = getProperty("exposed.database.driver"),
            user = getProperty("exposed.database.username"),
            password = getProperty("exposed.database.password")
        )

        declareMock<EventRepositoryImp>()

        eventRequest = Event(
            "Edited",
            Issue(
                12345678,
                "this is edited 2",
                "open",
                "2019-02-12T10:00:00",
                "2019-02-12T10:00:00"

            ))
    }

    @AfterEach
    fun stop() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun `Create event successfully`() {
        val eventService = get<EventService>()
        val expected = Event(
            "Edited",
            Issue(
                12345678,
                "this is edited 2",
                "open",
                "2019-02-12T10:00:00.000-02:00",
                "2019-02-12T10:00:00.000-02:00"
            ))
        val result = eventService.save(eventRequest)
        Assertions.assertEquals(expected, result)
    }

    @Test
    fun `Get event by issue with sucess`() {
        val eventService = get<EventService>()
        val expected = listOf(Event(
            "Edited",
            Issue(
                12345678,
                "this is edited 2",
                "open",
                "2019-02-12T10:00:00.000-02:00",
                "2019-02-12T10:00:00.000-02:00"
            )))
        val result = eventService.getEventByIssue(12345678)
        Assertions.assertEquals(expected, result)

    }
}
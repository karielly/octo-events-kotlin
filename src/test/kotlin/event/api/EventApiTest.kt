package event.api

import Main
import io.javalin.Javalin

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.Properties

class EventApiTest {

    lateinit var main: Javalin
    private var baseUri = "http://localhost:"
    private var properties: Properties = Properties()

    @BeforeEach
    fun setUp() {
        main = Main().start()
        properties.load(javaClass.getResourceAsStream(("/application.conf")))
        baseUri += properties.getProperty("javalin.port")
    }

    @AfterEach
    fun stop() {
        main.stop()
    }

    @Test
    fun `Endpoint with sucess`() {
        val event = javaClass.getResourceAsStream("/json/event_request.json")

        val response = khttp.post(
            url = "$baseUri/events",
            data = event
        )
        Assertions.assertEquals(201, response.statusCode)
    }

    @Test
    fun `Endpoint not found`() {
        val response = khttp.get(
            url = "$baseUri/events/2000/issue"
        )
        Assertions.assertEquals(404, response.statusCode)
    }

    @Test
    fun `Endpoint request json invalid`(){
        val eventInvalid = javaClass.getResourceAsStream("/json/event_request_invalid.json")
        val response = khttp.post(
            url = "$baseUri/events",
            data = eventInvalid
        )
        Assertions.assertEquals(500, response.statusCode)
    }

    @Test
    fun `Endpoint events by issue with sucess`() {
        val response = khttp.get(
            url = "$baseUri/issues/987654321/events"
        )
        Assertions.assertEquals(200, response.statusCode)
        Assertions.assertEquals(1, response.jsonArray.length())
    }
}
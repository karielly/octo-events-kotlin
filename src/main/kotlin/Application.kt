import io.javalin.Javalin
import io.javalin.JavalinEvent
import issueevent.IssueEventModule
import issueevent.api.EventEndPoint
import org.jetbrains.exposed.sql.Database
import org.koin.standalone.KoinComponent
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.getProperty
import org.koin.standalone.inject
import java.util.*

class Main : KoinComponent {

    private val eventEndPoint by inject<EventEndPoint>()

    fun  start() = Javalin.create().apply {
        startKoin(listOf(IssueEventModule), extraProperties = Properties().apply {
            load(javaClass.getResourceAsStream("/application.conf"))
            }.entries.associate {
                it.key.toString() to it.value.toString()
            })

        Database.connect(
            url = getProperty("exposed.database.url"),
            driver = getProperty("exposed.database.driver"),
            user = getProperty("exposed.database.username"),
            password = getProperty("exposed.database.password")
        )

        error(404) { ctx -> ctx.json("not found") }
        event(JavalinEvent.SERVER_STOPPED) { stopKoin() }
        exception(Exception::class.java) { e, _ -> e.printStackTrace() }
        port(getProperty("javalin.port"))

        routes {
            eventEndPoint.addEndpoints()
        }
    }.start()
}

object ApplicationRun {

    @JvmStatic
    fun main(args: Array<String>) {
        Main().start()
    }
}
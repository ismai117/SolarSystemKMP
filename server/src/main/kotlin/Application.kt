import io.ktor.server.application.*
import io.ktor.server.netty.*
import plugins.configureMonitoring
import plugins.configureRouting
import plugins.configureSerialization

fun main(args: Array<String>) =  EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureMonitoring()
    configureRouting()
}
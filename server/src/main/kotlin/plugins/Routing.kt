package plugins

import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*
import routes.planets

fun Application.configureRouting() {
    routing {
        planets()
        // Static plugin. Try to access `/static/index.html`
        static {
            resources("static")
        }
    }
}
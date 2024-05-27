package cn.hanasaka.plugins

import cn.hanasaka.routes.illust.illustRouting
import cn.hanasaka.routes.search.searchRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        illustRouting()
        searchRouting()
    }
}

package cn.hanasaka.plugins

import cn.hanasaka.network.datasource.PixivDataSource
import io.ktor.network.sockets.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/details") {
            val pid = call.request.queryParameters["pid"]
            if (pid == null) {
                call.respondText("Oops: 请提供pid参数！")
                return@get
            }
            try {
                val resJson = PixivDataSource.getPixivResponseByPid(pid)
                val res = Json.encodeToString(resJson)
                call.respond(res)
            } catch (e: SocketTimeoutException) {
                call.respondText("Oops: 连接超时了！请重新尝试连接")
            }
        }
    }
}

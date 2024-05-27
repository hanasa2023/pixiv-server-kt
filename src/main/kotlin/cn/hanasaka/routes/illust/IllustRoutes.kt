package cn.hanasaka.routes.illust

import cn.hanasaka.network.datasource.PixivDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.illustRouting() {
	route("/api/illust") {
		get {
			//获取pid
			val pid = call.request.queryParameters["pid"]
			if (pid == null) {
				call.respondText(
					"Oops: 请提供pid参数！",
					status = HttpStatusCode.BadRequest,
				)
				return@get
			}
			try {
				val result = PixivDataSource.getPixivResponseByPid(pid)
				if (result.error)  throw Exception(result.message)
				else
                call.respond(result.body)
			} catch (e: Exception) {
				call.respondText("Oops: ${e.message}", status = HttpStatusCode.InternalServerError)
			}
		}
	}
}
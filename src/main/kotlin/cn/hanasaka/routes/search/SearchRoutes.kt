package cn.hanasaka.routes.search

import cn.hanasaka.model.SearchIllustrationResponse
import cn.hanasaka.network.datasource.PixivDataSource
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.searchRouting() {
	route("/api/search") {
		post {
			//获取请求体
			val postBody = call.receive<PostBody>()

			//判断是否提供tag参数，如果没有则返回错误信息
			if (postBody.tag == null) {
				call.respondText(
					text = "Oops: 请提供tag参数！",
					status = HttpStatusCode.BadRequest,
				)
				return@post
			}

			val options = mapOf(
				"order" to orderType[postBody.order]!!,
				"blt" to postBody.blt.toString(),
				"s_mode" to modeType[postBody.mode]!!,
				"type" to searchType[postBody.type]!!,
				"mode" to restrictType[postBody.restrict]!!
			)

			//TODO: 优化代码结构，改良复用(你都用kotlin了还写这么愚蠢的代码……)
			try {
				when (postBody.type) {
					"illust" -> {
						val result = PixivDataSource.searchIllust(
							searchPath[postBody.type]!!,
							postBody.tag,
							options
						)
						//判断搜索是否出现错误，若有则返回错误信息
						if (result.error) {
							call.respondText(
								text = "Oops: ${result.message}",
								status = HttpStatusCode.InternalServerError
							)
							return@post
						}
						call.respond(result.body)
					}

					"manga" -> {
						val result = PixivDataSource.searchManga(
							searchPath[postBody.type]!!,
							postBody.tag,
							options
						)
						//判断搜索是否出现错误，若有则返回错误信息
						if (result.error) {
							call.respondText(
								text = "Oops: ${result.message}",
								status = HttpStatusCode.InternalServerError
							)
							return@post
						}
						call.respond(result.body)
					}

					"artworks" -> {
						val result = PixivDataSource.searchArtworks(
							searchPath[postBody.type]!!,
							postBody.tag,
							options
						)
						//判断搜索是否出现错误，若有则返回错误信息
						if (result.error) {
							call.respondText(
								text = "Oops: ${result.message}",
								status = HttpStatusCode.InternalServerError
							)
							return@post
						}
						call.respond(result.body)
					}
					else -> {
						call.respondText(
							text = "Oops: 未知的搜索类型！",
							status = HttpStatusCode.BadRequest
						)
					}
				}
			} catch (e: Exception) {
				call.respondText(
					text = "Oops: ${e.message}",
					status = HttpStatusCode.InternalServerError
				)
			}
		}
	}
}

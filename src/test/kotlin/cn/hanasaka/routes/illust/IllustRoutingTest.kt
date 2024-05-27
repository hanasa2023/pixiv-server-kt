package cn.hanasaka.routes.illust

import cn.hanasaka.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class IllustRoutingTest {

	@Test
	fun testGetApiIllust() = testApplication {
		application {
			configureRouting()
		}
		val response = client.get("/api/illust?pid=110141934")
		assertEquals(HttpStatusCode.OK, response.status)
		assertEquals(
			"application/json; charset=UTF-8",
			response.headers["Content-Type"]
		)
	}
}
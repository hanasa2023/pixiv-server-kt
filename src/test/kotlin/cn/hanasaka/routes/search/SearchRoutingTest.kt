package cn.hanasaka.routes.search

import cn.hanasaka.plugins.configureRouting
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchRoutingTest {

	@Test
	fun testPostApiSearch() = testApplication {
		val client = createClient {
			install(ContentNegotiation) {
				json()
			}
		}
		application {
			configureRouting()
		}
		val response = client.post("/api/search") {
			contentType(ContentType.Application.Json)
			setBody(PostBody(tag = "komari"))
		}
		assertEquals(HttpStatusCode.OK, response.status)
	}
}
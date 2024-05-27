package cn.hanasaka

import cn.hanasaka.plugins.*
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.autohead.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
	install(AutoHeadResponse)
	configureRouting()
	configureSerialization()
}

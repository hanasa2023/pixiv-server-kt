package cn.hanasaka.routes.search

import kotlinx.serialization.Serializable

/**
 *  post请求体的数据类
 */
@Serializable
data class PostBody(
	val tag: String? = null,
	val type: String = "illust",
	val order: String = "date",
	val blt: Int = 0,
	val mode: String = "full_tag",
	val restrict: String = "safe",
)

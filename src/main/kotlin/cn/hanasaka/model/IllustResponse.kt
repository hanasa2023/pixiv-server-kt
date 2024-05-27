package cn.hanasaka.model

import kotlinx.serialization.Serializable

/**
 * 获取Pid搜索详情的响应数据类
 */
@Serializable
data class IllustResponse(
    val error: Boolean,
    val message: String,
    val body: BodyData
)

@Serializable
data class BodyData(
	val illust_details: Illust,
	val author_details: Author
)

@Serializable
data class Illust(
	val url: String,
	val tags: List<String>,
	val illust_images: List<IllustImage>,
    val manga_a: List<MangaA>? = null,
	val url_s: String,
	val url_ss: String,
	val url_big: String,
	val url_placeholder: String,
)

@Serializable
data class IllustImage(
    val illust_image_width: String,
    val illust_image_height: String
)

@Serializable
data class MangaA(
    val page: Int,
    val url: String,
    val url_small: String,
    val url_big: String,
)

@Serializable
data class Author(
    val user_id: String,
    val user_name: String,
    val user_account: String
)

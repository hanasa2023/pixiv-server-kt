package cn.hanasaka.model

import kotlinx.serialization.Serializable

/**
 *  插画搜索的响应数据类
 */
@Serializable
data class SearchIllustrationResponse(
	val error: Boolean,
	val message: String? = null,
	val body: IllustrationBodyData
)

@Serializable
data class IllustrationBodyData(
	val illust: IllustData
)

@Serializable
data class IllustData(
	val data: List<DataBody>
)

/**
 *  漫画搜索的响应数据类
 */
@Serializable
data class SearchMangaResponse(
	val error: Boolean,
	val message: String? = null,
	val body: MangaBodyData
)

@Serializable
data class MangaBodyData(
	val manga: MangaData
)

@Serializable
data class MangaData(
	val data: List<DataBody>
)

/**
 *  作品（漫画&插画）搜索的响应数据类
 */
@Serializable
data class SearchArtworksResponse(
	val error: Boolean,
	val message: String? = null,
	val body: ArtworksBodyData
)

@Serializable
data class ArtworksBodyData(
	val illustManga: ArtworksData
)

@Serializable
data class ArtworksData(
	val data: List<DataBody>
)

@Serializable
data class DataBody(
	val id: String? = null,
	val title: String? = null,
	val tags:List<String>? = null,
	val userId: String? = null,
	val userName: String? = null,
	val width: Int? = null,
	val height: Int? = null,
	val createDate: String? = null,
	val updateDate: String? = null,
	val aiType: Int? = null
)

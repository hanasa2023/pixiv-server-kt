package cn.hanasaka.network.service

import cn.hanasaka.model.IllustResponse
import cn.hanasaka.model.SearchArtworksResponse
import cn.hanasaka.model.SearchIllustrationResponse
import cn.hanasaka.model.SearchMangaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Pixiv API的服务接口
 */
interface PixivApiService {
	@GET("touch/ajax/illust/details")
	suspend fun getIllustDetailsByPid(
		@Query("illust_id") pid: String,
	): IllustResponse

	@GET("ajax/search/{path}/{word}")
	suspend fun searchIllust(
		@Path("path") path: String,
		@Path("word") word: String,
		@QueryMap options: Map<String, String>,
	): SearchIllustrationResponse

	@GET("ajax/search/{path}/{word}")
	suspend fun searchManga(
		@Path("path") path: String,
		@Path("word") word: String,
		@QueryMap options: Map<String, String>,
	): SearchMangaResponse

	@GET("ajax/search/{path}/{word}")
	suspend fun searchArtworks(
		@Path("path") path: String,
		@Path("word") word: String,
		@QueryMap options: Map<String, String>,
	): SearchArtworksResponse
}

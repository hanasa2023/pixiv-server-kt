package cn.hanasaka.network.datasource

import cn.hanasaka.model.IllustResponse
import cn.hanasaka.model.SearchArtworksResponse
import cn.hanasaka.model.SearchIllustrationResponse
import cn.hanasaka.model.SearchMangaResponse
import cn.hanasaka.network.module.NetworkModule
import cn.hanasaka.network.service.PixivApiService
import cn.hanasaka.network.utils.Config
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

/**
 *  获取Pixiv作品详情的数据源的单例对象
 */

object PixivDataSource {
	private val _service = Retrofit.Builder()
		.baseUrl(Config.BASE_URL)
		.callFactory(NetworkModule.provideOkHttpClientCallFactory())
		.addConverterFactory(
			NetworkModule.provideNetworkJson()
				.asConverterFactory("application/json".toMediaType())
		)
		.build()
		.create(PixivApiService::class.java)

	/**
	 *  通过Pid获取Pixiv作品详情的实现
	 *  @param pid 作品的pid
	 *  @return IllustResponse
	 */
	suspend fun getPixivResponseByPid(
		pid: String,
	): IllustResponse = _service.getIllustDetailsByPid(pid)

	/**
	 *  搜索插画的实现
	 *  @param path 搜索类型
	 *  @param word 关键词
	 *  @param options post请求响应体
	 *  @return SearchIllustrationResponse
	 */
	suspend fun searchIllust(
		path: String,
		word: String,
		options: Map<String, String>,
	): SearchIllustrationResponse = _service.searchIllust(path, word, options)

	/**
	 *  搜索漫画的实现
	 *  @param path 搜索类型
	 *  @param word 关键词
	 *  @param options post请求响应体
	 *  @return SearchMangaResponse
	 */
	suspend fun searchManga(
		path: String,
		word: String,
		options: Map<String, String>,
	): SearchMangaResponse = _service.searchManga(path, word, options)

	/**
	 * 搜索作品的实现
	 * @param path 搜索类型
	 * @param word 关键词
	 * @param options post请求响应体
	 * @return SearchArtworksResponse
	 */
	suspend fun searchArtworks(
		path: String,
		word: String,
		options: Map<String, String>,
	): SearchArtworksResponse = _service.searchArtworks(path, word, options)
}

package cn.hanasaka.network.datasource

import cn.hanasaka.network.model.PixivResponse
import cn.hanasaka.network.module.NetworkModule
import cn.hanasaka.network.service.PixivApiService
import cn.hanasaka.network.utils.Config
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.Query

/**
 *  获取Pixiv作品详情的数据源的单例对象
 */

//TODO: 添加搜索模块，能够通过tag、画师uid等获取作品详情
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
     *  通过Pid获取Pixiv作品详情的Json数据
     *  @param pid 作品的pid
     */
    suspend fun getPixivResponseByPid(
        @Query("illust_id") pid: String
    ): PixivResponse = _service.getIllustDetailsByPid(pid)
}

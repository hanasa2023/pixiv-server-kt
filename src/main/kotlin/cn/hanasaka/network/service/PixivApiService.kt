package cn.hanasaka.network.service

import cn.hanasaka.network.model.PixivResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Pixiv API的服务接口
 */
interface PixivApiService {
    /**
     * 通过pid获取Pixiv作品详情的服务接口
     * @param pid 作品的pid
     */
    @GET("touch/ajax/illust/details")
    suspend fun getIllustDetailsByPid(
        @Query("illust_id") pid: String,
    ): PixivResponse

    //TODO: 添加搜索接口，能够通过tag、画师uid等获取作品详情
}

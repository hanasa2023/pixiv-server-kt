package cn.hanasaka.network.module

import cn.hanasaka.network.utils.Config
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.concurrent.TimeUnit

/**
 * Retrofit网络模块的单例对象
 */
object NetworkModule {
    private val _proxyAddress = InetSocketAddress(Config.PROXY_HOST, Config.PROXY_PORT)
    private val _proxy = Proxy(Proxy.Type.HTTP, _proxyAddress)

    /**
     * 提供OkHttpClient的CallFactory
     * @return OkHttpClient
     */
    fun provideOkHttpClientCallFactory() : OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .proxy(_proxy)
            .build()

    /**
     * 提供Json解析器
     * @return Json
     */
    fun provideNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }
}
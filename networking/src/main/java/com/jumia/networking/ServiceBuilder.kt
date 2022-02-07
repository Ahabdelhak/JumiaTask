package com.jumia.networking

import com.jumia.networking.PolymorphicConverter.PolymorphicRequestBodyConverter.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {

    fun buildService(interceptors: List<Interceptor>, debugTimeOut: Long, releaseTimeOut: Long): APIService {
        val client = OkHttpClient.Builder()

        interceptors.forEach {
            client.addInterceptor(it)
        }

        client.connectTimeout(if (BuildConfig.DEBUG) debugTimeOut else releaseTimeOut, TimeUnit.SECONDS)
            .readTimeout(if (BuildConfig.DEBUG) debugTimeOut else releaseTimeOut, TimeUnit.SECONDS)
            .writeTimeout(if (BuildConfig.DEBUG) debugTimeOut else releaseTimeOut, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .client(client.build())
            .baseUrl("http://nd7d1.mocklab.io/")
            .addConverterFactory(newFactory(JumiaRequest::class.java))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(APIService::class.java)
    }
}
package com.jumia.networking

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor

object NetworkController {

    var request: APIService? = null

    fun init(
        interceptors: List<Interceptor> = listOf(),
        debugTimeOut: Long = 30,
        releaseTimeOut: Long = 10
    ): NetworkController {
        request = ServiceBuilder.buildService(interceptors, debugTimeOut, releaseTimeOut)
        return this
    }

    suspend inline fun <reified T> processRequest(fullUrl: String, type: RequestType): T {
        if (request == null)
            throw NullPointerException("Please call NetworkController.init() in your Application class")

        val response = when (type) {
            is RequestType.GET -> request!!.getRequest(fullUrl, type.headers, type.queries)
            is RequestType.POST -> request!!.postRequest(fullUrl, type.body, type.headers)
            is RequestType.PUT -> request!!.putRequest(fullUrl, type.body, type.headers)
            is RequestType.PATCH -> request!!.patchRequest(fullUrl, type.body, type.headers)
            is RequestType.DELETE -> request!!.deleteRequest(fullUrl)
        }

        return Gson().fromJson(response, object : TypeToken<T>() {}.type)
    }

}
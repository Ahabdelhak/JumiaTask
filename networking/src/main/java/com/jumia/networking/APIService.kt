package com.jumia.networking

import com.google.gson.JsonElement
import retrofit2.http.*

interface APIService {

    @GET()
    suspend fun getRequest(
        @Url fullUrl: String,
        @HeaderMap headers: HashMap<String, Any>,
        @QueryMap queries: HashMap<String, Any>
    ): JsonElement

    @POST()
    suspend fun postRequest(
        @Url fullUrl: String,
        @Body requestBody: JumiaRequest,
        @HeaderMap headers: HashMap<String, Any>
    ): JsonElement

    @PUT()
    suspend fun putRequest(
        @Url fullUrl: String,
        @Body requestBody: JumiaRequest,
        @HeaderMap headers: HashMap<String, Any>
    ): JsonElement

    @PATCH()
    suspend fun patchRequest(
        @Url fullUrl: String,
        @Body requestBody: JumiaRequest,
        @HeaderMap headers: HashMap<String, Any>
    ): JsonElement

    @DELETE()
    suspend fun deleteRequest(@Url fullUrl: String): JsonElement

}
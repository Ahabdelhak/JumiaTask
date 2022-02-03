package com.jumia.networking

sealed class RequestType {

    data class GET(
        val headers: HashMap<String, Any> = hashMapOf(),
        val queries: HashMap<String, Any> = hashMapOf()
    ) : RequestType()

    data class POST(
        val body: JumiaRequest = EmptyRequest(),
        val headers: HashMap<String, Any> = hashMapOf()
    ) : RequestType()

    data class PUT(
        val body: JumiaRequest = EmptyRequest(),
        val headers: HashMap<String, Any> = hashMapOf()
    ) : RequestType()

    data class PATCH(
        val body: JumiaRequest = EmptyRequest(),
        val headers: HashMap<String, Any> = hashMapOf()
    ) : RequestType()

    object DELETE : RequestType()

}
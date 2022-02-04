package android.ptc.com.ptcflixing.data.remote

import android.ptc.com.ptcflixing.BuildConfig

object ApiEndpoints {

    private const val BASE_URL = BuildConfig.SERVER_URL
    const val Config = BASE_URL + "configurations/"

    fun searchProducts(query:String,page:Int): String {
     return BASE_URL + "search/"+query+"/page/"+page+"/"
    }
}
package android.ptc.com.ptcflixing.base

import android.app.Application
import com.jumia.networking.NetworkController
import okhttp3.logging.HttpLoggingInterceptor

class JumiaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setUpNetworkKit()
    }

    private fun setUpNetworkKit() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        NetworkController.init(listOf(logging))
    }

}
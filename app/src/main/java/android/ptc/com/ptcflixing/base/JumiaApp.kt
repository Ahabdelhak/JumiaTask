package android.ptc.com.ptcflixing.base

import android.app.Application
import android.ptc.com.ptcflixing.di.repositoryModule
import android.ptc.com.ptcflixing.di.viewModelModule
import com.jumia.networking.NetworkController
import com.wassma.softpos.cache.Cache
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JumiaApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
        setUpNetworkKit()
        startKoin {
            androidContext(this@JumiaApp)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }

    private fun setUpNetworkKit() {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        NetworkController.init(listOf(logging))
    }

    companion object {
        private lateinit var appInstance: JumiaApp
        var cache: Cache? = null

        fun getAppInstance(): JumiaApp {
            return appInstance
        }
    }

}
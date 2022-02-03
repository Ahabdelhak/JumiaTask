package android.ptc.com.ptcflixing.base

import android.app.Application
import android.ptc.com.ptcflixing.di.repositoryModule
import android.ptc.com.ptcflixing.di.viewModelModule
import com.jumia.networking.NetworkController
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class JumiaApp : Application() {

    override fun onCreate() {
        super.onCreate()
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

}
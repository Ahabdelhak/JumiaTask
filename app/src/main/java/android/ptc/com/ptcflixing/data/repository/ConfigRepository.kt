package android.ptc.com.ptcflixing.data.repository

import android.ptc.com.ptcflixing.data.model.Config
import android.ptc.com.ptcflixing.data.remote.ApiEndpoints
import com.jumia.networking.NetworkController
import com.jumia.networking.RequestType

class ConfigRepository {
    suspend fun config(): Config {
        return NetworkController.processRequest(
            ApiEndpoints.Config,
            RequestType.GET()
        )
    }
}
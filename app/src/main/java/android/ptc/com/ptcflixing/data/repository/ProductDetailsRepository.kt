package android.ptc.com.ptcflixing.data.repository

import android.ptc.com.ptcflixing.data.model.ProductDetails
import android.ptc.com.ptcflixing.data.remote.ApiEndpoints
import com.jumia.networking.NetworkController
import com.jumia.networking.RequestType

class ProductDetailsRepository {
    suspend fun productDetails(productId:Int): ProductDetails {
        return NetworkController.processRequest(
            ApiEndpoints.productDetails(productId),
            RequestType.GET()
        )
    }
}
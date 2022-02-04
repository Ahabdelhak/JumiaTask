package android.ptc.com.ptcflixing.data.repository

import android.ptc.com.ptcflixing.data.model.ProductSearch
import android.ptc.com.ptcflixing.data.remote.ApiEndpoints
import com.jumia.networking.NetworkController
import com.jumia.networking.RequestType

class ProductSearchRepository {
    suspend fun productSearch(searchQuery:String,page:Int): ProductSearch {
        return NetworkController.processRequest(
            ApiEndpoints.searchProducts(searchQuery,page),
            RequestType.GET()
        )
    }
}
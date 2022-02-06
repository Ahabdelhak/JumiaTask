package android.ptc.com.ptcflixing.ui.details

import android.ptc.com.ptcflixing.base.BaseViewModel
import android.ptc.com.ptcflixing.data.repository.ProductDetailsRepository

class ProductDetailsViewModel (private val repository: ProductDetailsRepository) : BaseViewModel() {
    fun getProductDetails(productId:String) = safeCall { repository.productDetails(productId) }
}
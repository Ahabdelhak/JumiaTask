package android.ptc.com.ptcflixing.ui.home

import android.ptc.com.ptcflixing.base.BaseViewModel
import android.ptc.com.ptcflixing.data.repository.ProductSearchRepository

class ProductSearchViewModel (private val repository: ProductSearchRepository) : BaseViewModel() {
    fun getProductResult(searchQuery:String,page:Int) = safeCall { repository.productSearch(searchQuery,page) }
}
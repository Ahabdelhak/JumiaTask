package android.ptc.com.ptcflixing.Adapter

import android.content.Context
import android.graphics.Paint
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.data.model.ProductSearch
import android.ptc.com.ptcflixing.databinding.ProductItemBinding
import android.ptc.com.ptcflixing.util.getCacheInstance
import android.ptc.com.ptcflixing.util.priceFormatter
import android.ptc.com.ptcflixing.util.savingFormatter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductHolder>(){
    private lateinit var currency: String
    private var productList: MutableList<ProductSearch.Metadata.Result> = ArrayList()
    private var context: Context? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductHolder {
        val binding: ProductItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.product_item, viewGroup, false
        )
        context = viewGroup.context
        currency = getCacheInstance().config?.metadata?.currency?.currencySymbol + " "
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = productList[position]
        holder.binding.productName.text = product.name
        holder.binding.brandName.text = product.brand
        holder.binding.specialPrice.text = product.specialPrice.priceFormatter(currency,",")
        holder.binding.savingPercentage.text = product.maxSavingPercentage.savingFormatter()
        holder.binding.price.text = product.price.priceFormatter(currency,",")
        holder.binding.price.paintFlags = holder.binding.price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        product.ratingAverage?.let { holder.binding.productRate.rating = product.ratingAverage.toFloat() }

        Glide.with(context!!)
            .load(product.image)
            .placeholder(R.drawable.jumia)
            .error(R.drawable.jumia)
            .into(holder.binding.productImg)

    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun addList(list: List<ProductSearch.Metadata.Result>?) {
        productList.addAll(list!!)
        notifyDataSetChanged()
    }

    fun productList(): MutableList<ProductSearch.Metadata.Result> {
        return productList
    }

    fun reset() {
        productList.clear()
        notifyDataSetChanged()
    }

    class ProductHolder(var binding: ProductItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

}
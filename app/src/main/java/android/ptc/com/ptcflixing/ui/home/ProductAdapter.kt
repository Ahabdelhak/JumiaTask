package android.ptc.com.ptcflixing.ui.home

import android.content.Context
import android.graphics.Paint
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.data.model.ProductSearch
import android.ptc.com.ptcflixing.databinding.ProductItemBinding
import android.ptc.com.ptcflixing.util.getCacheInstance
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.EpisodeHolder>(){
    private lateinit var currency: String
    private var productList: MutableList<ProductSearch.Metadata.Result> = ArrayList()
    var context: Context? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EpisodeHolder {
        val binding: ProductItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.product_item, viewGroup, false
        )
        context = viewGroup.context
        currency = getCacheInstance().config?.metadata?.currency?.currencySymbol + " "
        return EpisodeHolder(binding)
    }

    override fun onBindViewHolder(holder: EpisodeHolder, position: Int) {
        val product = productList[position]
        holder.binding.productName.text = product.name
        holder.binding.brandName.text = product.brand
        holder.binding.specialPrice.text = currency + product.specialPrice.toString()
        holder.binding.savingPercentage.text = product.maxSavingPercentage.toString()+ " %"
        holder.binding.price.text = currency + product.price.toString()
        holder.binding.price.paintFlags = holder.binding.price.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        product.ratingAverage?.let { holder.binding.productRate.rating = product.ratingAverage.toFloat() }

        Glide.with(context!!)
            .load("http://via.placeholder.com/300.png")
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

    fun reset() {
        productList.clear()
        notifyDataSetChanged()
    }

    class EpisodeHolder(var binding: ProductItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

}
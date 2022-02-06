package android.ptc.com.ptcflixing.Adapter

import android.content.Context
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.databinding.ProductImageItemBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList

class ProductImagesAdapter : RecyclerView.Adapter<ProductImagesAdapter.ProductHolder>(){
    private var imageList: MutableList<String> = ArrayList()
    private var context: Context? = null
    var selectedIndex: Int = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ProductHolder {
        val binding: ProductImageItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.product_image_item, viewGroup, false
        )
        context = viewGroup.context
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val productUrl = imageList[position]
        Glide.with(context!!)
            .load(productUrl)
            .placeholder(R.drawable.jumia)
            .error(R.drawable.jumia)
            .into(holder.binding.imageView)

        if (selectedIndex==position)
            holder.binding.bgImage.background=context?.getDrawable(R.drawable.bg_orange_border)
        else holder.binding.bgImage.background=context?.getDrawable(R.drawable.bg_border)

    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    fun addList(list: List<String>?) {
        imageList.addAll(list!!)
        notifyDataSetChanged()
    }

    fun imageList(): MutableList<String> {
        return imageList
    }

    class ProductHolder(var binding: ProductImageItemBinding) : RecyclerView.ViewHolder(
        binding.root
    )

}
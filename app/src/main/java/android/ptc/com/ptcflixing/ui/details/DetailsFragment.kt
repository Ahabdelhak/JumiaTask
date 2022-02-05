package android.ptc.com.ptcflixing.ui.details

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.data.model.ProductDetails
import android.ptc.com.ptcflixing.data.remote.Resource
import android.ptc.com.ptcflixing.databinding.FragmentDetailsBinding
import android.ptc.com.ptcflixing.util.getCacheInstance
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.android.ext.android.inject

class DetailsFragment : Fragment() {
    private val viewModel: ProductDetailsViewModel by inject()
    var binding: FragmentDetailsBinding? = null
    private lateinit var currency: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currency = getCacheInstance().config?.metadata?.currency?.currencySymbol + " "
        getProductDetails(1)
    }

    private fun getProductDetails(productId: Int) {
        viewModel.getProductDetails(productId)
            .observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading ->loading.visibility=View.VISIBLE
                    is Resource.Success -> setProductValues(it.data?.metadata)
                    is Resource.Error -> {
                        loading.visibility=View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()}
                }
            }
    }

    private fun setProductValues(productData: ProductDetails.Metadata?) {
        loading.visibility=View.GONE
        binding?.specialPriceView?.text=currency + productData?.specialPrice.toString()
        binding?.priceView?.text=currency + productData?.price.toString()
        binding?.priceView?.paintFlags = binding?.priceView?.paintFlags!! or Paint.STRIKE_THRU_TEXT_FLAG

        binding?.savingPercentageView?.text=productData?.maxSavingPercentage.toString() + " %"
        binding?.ratingsTotal?.text=productData?.rating?.ratingsTotal.toString()
        binding?.ratingView?.let { binding?.ratingView?.rating = productData?.rating?.average?.toFloat()!! }
        binding?.descriptionView?.text=productData?.summary?.description.toString()

        binding?.productImage?.let {
            Glide.with(requireContext())
                .load(productData?.imageList?.get(0))
                .placeholder(R.drawable.jumia)
                .error(R.drawable.jumia)
                .into(it)
        }
    }

}
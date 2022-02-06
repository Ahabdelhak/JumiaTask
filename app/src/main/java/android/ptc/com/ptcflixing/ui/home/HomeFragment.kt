package android.ptc.com.ptcflixing.ui.home

import android.os.Bundle
import android.ptc.com.ptcflixing.Adapter.ProductAdapter
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.data.remote.Resource
import android.ptc.com.ptcflixing.databinding.FragmentHomeBinding
import android.ptc.com.ptcflixing.util.Constants.PRODUCT_ID
import android.ptc.com.ptcflixing.util.EndlessRecyclerViewScrollListener
import android.ptc.com.ptcflixing.util.RecyclerItemClickListener
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arlib.floatingsearchview.FloatingSearchView.OnSearchListener
import kotlinx.android.synthetic.main.custom_search_bar.*
import org.koin.android.ext.android.inject
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion

class HomeFragment : Fragment() {
    var binding: FragmentHomeBinding? = null
    private var mLastQuery: String?=null
    private val viewModel: ProductSearchViewModel by inject()
    lateinit var layoutManager: GridLayoutManager
    lateinit var scrollListener: EndlessRecyclerViewScrollListener
    lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity()?.finish()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProductRv()
        searchView.setOnSearchListener(object : OnSearchListener {
            override fun onSuggestionClicked(searchSuggestion: SearchSuggestion) {

            }
            override fun onSearchAction(query: String) {
                mLastQuery = query
                getSearchResult(1)
            }
        })
    }

    private fun initProductRv() {
        layoutManager = GridLayoutManager(activity,2)
        binding!!.productRv.layoutManager = layoutManager
        adapter = ProductAdapter()
        binding!!.productRv.adapter = adapter
        binding!!.productRv.addOnItemTouchListener(RecyclerItemClickListener(requireContext(),object : RecyclerItemClickListener.OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                findNavController().navigate(R.id.detailsFragment,bundleOf(PRODUCT_ID to adapter.productList()[position].sku))
            }

        }))

        scrollListener = object : EndlessRecyclerViewScrollListener(layoutManager){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                var pageCount = page
                pageCount++
                getSearchResult(pageCount)
            }

        }
        binding!!.productRv.addOnScrollListener(scrollListener)
    }

    private fun getSearchResult(page: Int) {
        viewModel.getProductResult(mLastQuery!!,page)
            .observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Loading -> binding?.loading?.visibility=View.VISIBLE
                    is Resource.Success -> {
                        binding?.loading?.visibility=View.GONE
                        if(it.data?.success!!){
                            if (page == 1) adapter.reset()
                            adapter.addList(it.data?.metadata?.results)
                        }else Toast.makeText(requireContext(), it.data.messages.error.message, Toast.LENGTH_SHORT).show()}
                    is Resource.Error -> {
                        binding?.loading?.visibility=View.GONE
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()}
                }
            }
    }
}
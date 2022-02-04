package android.ptc.com.ptcflixing.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.data.remote.Resource
import android.ptc.com.ptcflixing.util.getCacheInstance
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashFragment : Fragment() {

    private val splashDelayDuration: Long = 1500
    private val viewModel: ConfigViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            delay(splashDelayDuration)
            getAppConfig()
        }
    }

    private fun getAppConfig(){
        viewModel.getConfig()
            .observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        getCacheInstance().config=it.data
                        findNavController().navigate(R.id.homeFragment)}
                    is Resource.Error -> Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
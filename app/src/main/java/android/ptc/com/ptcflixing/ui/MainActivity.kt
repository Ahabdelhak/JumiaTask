package android.ptc.com.ptcflixing.ui

import android.os.Bundle
import android.ptc.com.ptcflixing.R
import android.ptc.com.ptcflixing.data.remote.Resource
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    private val viewModel: ConfigViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.getConfig()
            .observe(this) {
                when (it) {
                    is Resource.Success -> Log.e(TAG,it.data?.success.toString())
                    is Resource.Error -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}

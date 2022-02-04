package android.ptc.com.ptcflixing.util

import android.app.Activity
import android.ptc.com.ptcflixing.base.JumiaApp
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wassma.softpos.cache.Cache

fun getCacheInstance(): Cache {
    if(JumiaApp.cache == null){
        JumiaApp.cache = Cache()
    }
    return JumiaApp.cache!!
}

/*Toast*/
fun Activity.toast(text: String?) =
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

fun Fragment.toast(text: String?) {
    if (activity != null) {
        requireActivity().toast(text)
    }
}
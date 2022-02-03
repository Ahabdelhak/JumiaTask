package android.ptc.com.ptcflixing.base

import android.ptc.com.ptcflixing.data.remote.Resource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

open class BaseViewModel : ViewModel() {
    fun <T> safeCall(apiMethod: suspend () -> T) = liveData {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(apiMethod.invoke()))
        } catch (throwable: Throwable) {
            "Something went wrong"
        }
    }
}
package android.ptc.com.ptcflixing.data.remote

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    object Loading : Resource<Nothing>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String) : Resource<T>(message = message)
}
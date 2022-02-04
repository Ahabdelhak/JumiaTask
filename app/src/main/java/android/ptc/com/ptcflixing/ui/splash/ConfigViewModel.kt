package android.ptc.com.ptcflixing.ui.splash

import android.ptc.com.ptcflixing.base.BaseViewModel
import android.ptc.com.ptcflixing.data.repository.ConfigRepository

class ConfigViewModel (private val repository: ConfigRepository) : BaseViewModel() {
    fun getConfig() = safeCall { repository.config() }
}
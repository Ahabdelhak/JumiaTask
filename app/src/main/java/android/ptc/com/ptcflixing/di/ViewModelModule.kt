package android.ptc.com.ptcflixing.di

import android.ptc.com.ptcflixing.ui.splash.ConfigViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ConfigViewModel(get()) }
}
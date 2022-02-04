package android.ptc.com.ptcflixing.di

import android.ptc.com.ptcflixing.data.repository.ConfigRepository
import android.ptc.com.ptcflixing.data.repository.ProductSearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { ConfigRepository() }
    factory { ProductSearchRepository() }
}
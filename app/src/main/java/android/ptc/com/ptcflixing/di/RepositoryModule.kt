package android.ptc.com.ptcflixing.di

import android.ptc.com.ptcflixing.data.repository.ConfigRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory { ConfigRepository() }
}
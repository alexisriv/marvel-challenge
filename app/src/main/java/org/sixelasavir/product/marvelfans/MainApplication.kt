package org.sixelasavir.product.marvelfans

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.sixelasavir.product.marvelfans.api.CharacterApi
import org.sixelasavir.product.marvelfans.api.ClientBuilder
import org.sixelasavir.product.marvelfans.api.EventApi
import org.sixelasavir.product.marvelfans.api.repositories.CharacterRepository
import org.sixelasavir.product.marvelfans.api.repositories.CharacterRepositoryImpl
import org.sixelasavir.product.marvelfans.api.repositories.EventRepository
import org.sixelasavir.product.marvelfans.api.repositories.EventRepositoryImpl
import org.sixelasavir.product.marvelfans.ui.viewmodel.CharacterDetailViewModel
import org.sixelasavir.product.marvelfans.ui.viewmodel.CharacterViewModel
import org.sixelasavir.product.marvelfans.ui.viewmodel.EventViewModel

class MainApplication : Application() {

    private val characterApi = ClientBuilder.service(CharacterApi::class)
    private val eventApi = ClientBuilder.service(EventApi::class)
    private val appModule = module {
        single<CharacterRepository> { CharacterRepositoryImpl(api = characterApi) }
        single<EventRepository> { EventRepositoryImpl(api = eventApi) }
        viewModel { CharacterViewModel(get()) }
        viewModel { EventViewModel(get())}
        viewModel { CharacterDetailViewModel(get())}
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}
package com.cerulik.scratchcard.di

import com.cerulik.scratchcard.data.ScratchCardRepository
import com.cerulik.scratchcard.data.ScratchCardRepositoryImpl
import com.cerulik.scratchcard.data.remote.api.ScratchCardApi
import com.cerulik.scratchcard.domain.ActivateCardUseCase
import com.cerulik.scratchcard.domain.GetScratchCardStateUseCase
import com.cerulik.scratchcard.domain.ScratchCardUseCase
import com.cerulik.scratchcard.ui.activation.ActivationScreenViewModel
import com.cerulik.scratchcard.ui.home.HomeScreenViewModel
import com.cerulik.scratchcard.ui.scratch.ScratchScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<ScratchCardApi> {
        Retrofit.Builder()
            .baseUrl("https://api.o2.sk/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ScratchCardApi::class.java)
    }
    single<ScratchCardRepository> { ScratchCardRepositoryImpl(get(), Dispatchers.IO, MainScope()) }
    factory { GetScratchCardStateUseCase(get()) }
    factory { ScratchCardUseCase(get()) }
    factory { ActivateCardUseCase(get()) }
    viewModel { ScratchScreenViewModel(get(), get()) }
    viewModel { HomeScreenViewModel(get()) }
    viewModel { ActivationScreenViewModel(get(), get()) }
}
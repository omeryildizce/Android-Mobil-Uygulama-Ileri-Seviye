package com.omeryildizce.cryptocrazy.di

import com.omeryildizce.cryptocrazy.repository.CryptoDownload
import com.omeryildizce.cryptocrazy.repository.CryptoDownloadImpl
import com.omeryildizce.cryptocrazy.service.CryptoApi
import com.omeryildizce.cryptocrazy.viewmodel.CryptoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
     val BASE_URL = "https://raw.githubusercontent.com/"
     Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
         .create(CryptoApi::class.java)

    }

    single<CryptoDownload> {
        CryptoDownloadImpl(get())
    }

    viewModel{
        CryptoViewModel(get())
    }
    factory {

    }
}
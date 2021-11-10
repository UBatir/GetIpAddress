package com.example.getip.di

import com.example.getip.data.ApiInterface
import com.example.getip.ui.MainViewModel
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object C {
    const val baseUrl: String = "http://awstest-balancer-1233234915.us-east-2.elb.amazonaws.com/"
}

val remoteModule = module {
    single {
        GsonBuilder().setLenient().create()
    }
    single {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(25, TimeUnit.SECONDS)
            .readTimeout(25, TimeUnit.SECONDS)
            .writeTimeout(25, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .build()

    }
    single {
        Retrofit.Builder()
            .baseUrl(C.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(get()).build()
    }
    single { get<Retrofit>().create(ApiInterface::class.java) }
}

val viewModelModule = module {
    single { MainViewModel(get()) }
}
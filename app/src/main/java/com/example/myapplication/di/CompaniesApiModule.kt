package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.api.CompaniesApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideGson(): Gson {
    return GsonBuilder()
        .setStrictness(Strictness.LENIENT)
        .create()
}

fun provideHttpClient(context: Context): OkHttpClient {
    return OkHttpClient.Builder()
        .apply {
            readTimeout(30L, TimeUnit.SECONDS)
        }
        .addInterceptor(ChuckerInterceptor(context))
        .build()
}

fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://random-data-api.com/api/")
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun provideApi(retrofit: Retrofit): CompaniesApi = retrofit.create(CompaniesApi::class.java)

val companiesApiModule = module {
    single { provideGson() }
    single { provideHttpClient(androidContext()) }
    single { provideRetrofit(get(), get()) }
    single { provideApi(get()) }
}
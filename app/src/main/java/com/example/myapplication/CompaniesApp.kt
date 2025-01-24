package com.example.myapplication

import android.app.Application
import com.example.myapplication.di.CompaniesAppModule
import com.example.myapplication.di.companiesApiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CompaniesApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CompaniesApp)
            modules(companiesApiModule, CompaniesAppModule)
        }
    }

}
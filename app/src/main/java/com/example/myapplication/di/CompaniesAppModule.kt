package com.example.myapplication.di

import com.example.myapplication.data.factory.CompaniesApiFactory
import com.example.myapplication.data.repository.CompaniesApiRepository
import com.example.myapplication.presentation.viewModel.CompanyAppViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val CompaniesAppModule = module {
    factory { CompaniesApiFactory() }
    single { CompaniesApiRepository(get(), get()) }
    viewModel { CompanyAppViewModel(get()) }
}
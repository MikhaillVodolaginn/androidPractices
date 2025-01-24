package com.example.myapplication.data.repository

import com.example.myapplication.data.api.CompaniesApi
import com.example.myapplication.data.factory.CompaniesApiFactory
import com.example.myapplication.domain.model.Company
import com.example.myapplication.domain.repository.CompaniesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CompaniesApiRepository(
    private val companiesApi: CompaniesApi,
    private val companiesApiFactory: CompaniesApiFactory
) : CompaniesRepository {
    override suspend fun getCompanies(count: Int): List<Company> {
        return withContext(Dispatchers.IO) {
            val response = companiesApi.getApiCompanies(count)

            response.map {
                companiesApiFactory.convert(it)
            }
        }
    }

    override suspend fun getCompanyById(id: Int): Company {
        return withContext(Dispatchers.IO) {
            val response = companiesApi.getApiCompanyById(id)

            companiesApiFactory.convert(response)
        }
    }
}
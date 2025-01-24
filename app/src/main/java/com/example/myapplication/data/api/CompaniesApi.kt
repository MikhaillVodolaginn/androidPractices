package com.example.myapplication.data.api

import com.example.myapplication.data.response.CompanyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CompaniesApi {
    @GET("company/random_company")
    suspend fun getApiCompanies(
        @Query("size") count: Int
    ): List<CompanyResponse>

    @GET("company/random_company")
    suspend fun getApiCompanyById(
        @Query("id") id: Int
    ): CompanyResponse
}
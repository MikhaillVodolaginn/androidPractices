package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Company

interface CompaniesRepository {
    suspend fun getCompanies(count: Int): List<Company>
    suspend fun getCompanyById(id: Int): Company
}
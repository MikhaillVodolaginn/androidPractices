package com.example.myapplication.data.factory

import com.example.myapplication.data.response.CompanyResponse
import com.example.myapplication.domain.model.Company

class CompaniesApiFactory {
    fun convert(response: CompanyResponse): Company {
        return Company(
            response.id,
            response.businessName,
            response.industry,
            response.catchPhrase,
            response.logo,
            response.type,
            response.phoneNumber,
            response.fullAddress
        )
    }
}
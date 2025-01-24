package com.example.myapplication.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repository.CompaniesApiRepository
import com.example.myapplication.domain.model.Company
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CompanyAppViewModel(
    private val companiesApiRepository: CompaniesApiRepository
): ViewModel() {
    private val _companyList: MutableStateFlow<List<Company>> = MutableStateFlow(emptyList())
    val companyList: StateFlow<List<Company>> = _companyList.asStateFlow()

    private val _isLoadingList: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoadingList: StateFlow<Boolean> = _isLoadingList.asStateFlow()

    private val _errorLoadingList: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val errorLoadingList: StateFlow<Boolean> = _errorLoadingList.asStateFlow()

    private val _company: MutableStateFlow<Company?> = MutableStateFlow(null)
    val company: StateFlow<Company?> = _company.asStateFlow()

    init {
        loadCompanyList()
    }

    private fun loadCompanyList() {
        viewModelScope.launch {
            _errorLoadingList.value = false
            _isLoadingList.value = true
            try {
                /** По умолчанию запрашиваем 15 компаний */
                val companies = companiesApiRepository.getCompanies(15)
                _companyList.value = companies

                /** По умолчанию отображаем на деталке 1 компанию */
                _company.value = companies.firstOrNull()
            } catch (e: Exception) {
                _errorLoadingList.value = true
            } finally {
                _isLoadingList.value = false
            }
        }
    }

    fun updateCompanyList() {
        loadCompanyList()
    }

    fun getDetailCompany(id: Int) {
        viewModelScope.launch {
            val localCompany: Company? = _companyList.value.find { it.id == id }
            if (localCompany != null) {
                _company.value = localCompany
            } else {
                /** Позже планирую добавить возможность открывать на деталке компанию не из списка */
                _company.value = companiesApiRepository.getCompanyById(id)
            }
        }
    }

    fun getDefaultCompany(): Company {
        /** Заглушка на деталку, если не пришел список компаний */
        return Company(
            1,
            "Компания 1",
            "Лесопилка",
            "Пилили, пилим и будем пилить",
            null,
            "Производство",
            "9999999999",
            "Северный полюс дом 1"
        )
    }
}
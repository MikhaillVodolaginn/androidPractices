package com.example.myapplication.data.response

import com.google.gson.annotations.SerializedName


class CompanyResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("business_name")
    val businessName: String?,
    @SerializedName("industry")
    val industry: String?,
    @SerializedName("catch_phrase")
    val catchPhrase: String?,
    @SerializedName("logo")
    val logo: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("phone_number")
    val phoneNumber: String?,
    @SerializedName("full_address")
    val fullAddress: String?
)
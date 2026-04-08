package com.iip.userservice.application.dto

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import java.math.BigDecimal

data class CreateUserRequest(
    @field:NotBlank val name: String,
    @field:NotBlank
    @field:Pattern(regexp = "^\\d{10,15}$", message = "phone must be digits only, 10-15 chars")
    val phone: String,
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserFullResponse(
    val id: Long,
    val name: String,
    val phone: String,
    val preferences: PreferencesResponse?,
    val portfolio: List<PortfolioItemResponse>,
    val alerts: List<AlertItemResponse> = emptyList(),
)

data class PreferencesResponse(
    val riskProfile: String,
    val favoriteAssets: List<String>,
)

data class PortfolioItemResponse(
    val symbol: String,
    val quantity: BigDecimal,
    val averagePrice: BigDecimal,
)

data class AlertItemResponse(
    val symbol: String,
    val conditionType: String,
    val threshold: BigDecimal,
)

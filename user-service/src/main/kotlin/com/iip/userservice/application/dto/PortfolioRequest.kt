package com.iip.userservice.application.dto

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class PortfolioRequest(
    @field:NotBlank val symbol: String,
    @field:NotNull @field:DecimalMin("0.0000001") val quantity: BigDecimal,
    @field:NotNull @field:DecimalMin("0.0000001") val averagePrice: BigDecimal,
)

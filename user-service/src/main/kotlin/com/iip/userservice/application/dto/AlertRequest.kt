package com.iip.userservice.application.dto

import com.iip.userservice.domain.model.AlertConditionType
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class AlertRequest(
    @field:NotBlank val symbol: String,
    @field:NotNull val conditionType: AlertConditionType,
    @field:NotNull @field:DecimalMin("0.0000001") val threshold: BigDecimal,
)

package com.iip.userservice.domain.model

import java.math.BigDecimal

data class AlertConfig(
    val id: Long? = null,
    val userId: Long,
    val symbol: String,
    val conditionType: AlertConditionType,
    val threshold: BigDecimal,
)

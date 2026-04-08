package com.iip.userservice.domain.model

import java.math.BigDecimal

data class Portfolio(
    val id: Long? = null,
    val userId: Long,
    val symbol: String,
    val quantity: BigDecimal,
    val averagePrice: BigDecimal,
)

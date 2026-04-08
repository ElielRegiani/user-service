package com.iip.userservice.domain.repository

import com.iip.userservice.domain.model.Portfolio

interface PortfolioRepository {
    fun save(portfolio: Portfolio): Portfolio
    fun findByUserId(userId: Long): List<Portfolio>
    fun findByUserIdAndSymbol(userId: Long, symbol: String): Portfolio?
}

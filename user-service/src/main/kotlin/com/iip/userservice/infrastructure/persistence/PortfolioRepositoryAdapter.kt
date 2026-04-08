package com.iip.userservice.infrastructure.persistence

import com.iip.userservice.domain.model.Portfolio
import com.iip.userservice.domain.repository.PortfolioRepository
import com.iip.userservice.infrastructure.persistence.mapper.PortfolioMapper
import com.iip.userservice.infrastructure.persistence.repository.PortfolioJpaRepository
import org.springframework.stereotype.Component

@Component
class PortfolioRepositoryAdapter(
    private val jpa: PortfolioJpaRepository,
) : PortfolioRepository {
    override fun save(portfolio: Portfolio): Portfolio {
        val saved = jpa.save(PortfolioMapper.toEntity(portfolio))
        return PortfolioMapper.toDomain(saved)
    }

    override fun findByUserId(userId: Long): List<Portfolio> =
        jpa.findByUserId(userId).map(PortfolioMapper::toDomain)

    override fun findByUserIdAndSymbol(userId: Long, symbol: String): Portfolio? =
        jpa.findByUserIdAndSymbol(userId, symbol)?.let(PortfolioMapper::toDomain)
}

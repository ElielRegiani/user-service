package com.iip.userservice.infrastructure.persistence.repository

import com.iip.userservice.infrastructure.persistence.entity.PortfolioEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PortfolioJpaRepository : JpaRepository<PortfolioEntity, Long> {
    fun findByUserId(userId: Long): List<PortfolioEntity>

    fun findByUserIdAndSymbol(userId: Long, symbol: String): PortfolioEntity?
}

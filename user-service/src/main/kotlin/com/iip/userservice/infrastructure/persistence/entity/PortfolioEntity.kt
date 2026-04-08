package com.iip.userservice.infrastructure.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.math.BigDecimal

@Entity
@Table(
    name = "portfolio",
    uniqueConstraints = [
        UniqueConstraint(name = "uk_portfolio_user_symbol", columnNames = ["user_id", "symbol"]),
    ],
)
class PortfolioEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "user_id", nullable = false)
    var userId: Long = 0,

    @Column(nullable = false, length = 32)
    var symbol: String = "",

    @Column(nullable = false, precision = 19, scale = 8)
    var quantity: BigDecimal = BigDecimal.ZERO,

    @Column(name = "average_price", nullable = false, precision = 19, scale = 8)
    var averagePrice: BigDecimal = BigDecimal.ZERO,
)

package com.iip.userservice.infrastructure.persistence.entity

import com.iip.userservice.domain.model.AlertConditionType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint
import java.math.BigDecimal

@Entity
@Table(
    name = "alerts",
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_alert_user_symbol_condition",
            columnNames = ["user_id", "symbol", "condition_type"],
        ),
    ],
)
class AlertEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "user_id", nullable = false)
    var userId: Long = 0,

    @Column(nullable = false, length = 32)
    var symbol: String = "",

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_type", nullable = false, length = 20)
    var conditionType: AlertConditionType = AlertConditionType.SIGNAL,

    @Column(nullable = false, precision = 19, scale = 8)
    var threshold: BigDecimal = BigDecimal.ZERO,
)

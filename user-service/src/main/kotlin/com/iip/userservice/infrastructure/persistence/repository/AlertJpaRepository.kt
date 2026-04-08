package com.iip.userservice.infrastructure.persistence.repository

import com.iip.userservice.domain.model.AlertConditionType
import com.iip.userservice.infrastructure.persistence.entity.AlertEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AlertJpaRepository : JpaRepository<AlertEntity, Long> {
    fun findByUserId(userId: Long): List<AlertEntity>

    fun existsByUserIdAndSymbolAndConditionType(
        userId: Long,
        symbol: String,
        conditionType: AlertConditionType,
    ): Boolean
}

package com.iip.userservice.domain.repository

import com.iip.userservice.domain.model.AlertConditionType
import com.iip.userservice.domain.model.AlertConfig

interface AlertRepository {
    fun save(alert: AlertConfig): AlertConfig
    fun findByUserId(userId: Long): List<AlertConfig>
    fun existsByUserIdAndSymbolAndConditionType(
        userId: Long,
        symbol: String,
        conditionType: AlertConditionType,
    ): Boolean
}

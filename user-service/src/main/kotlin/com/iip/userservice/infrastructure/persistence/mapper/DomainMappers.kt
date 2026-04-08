package com.iip.userservice.infrastructure.persistence.mapper

import com.iip.userservice.domain.model.AlertConfig
import com.iip.userservice.domain.model.Portfolio
import com.iip.userservice.domain.model.Preferences
import com.iip.userservice.domain.model.User
import com.iip.userservice.infrastructure.persistence.entity.AlertEntity
import com.iip.userservice.infrastructure.persistence.entity.PortfolioEntity
import com.iip.userservice.infrastructure.persistence.entity.PreferencesEntity
import com.iip.userservice.infrastructure.persistence.entity.UserEntity

object UserMapper {
    fun toDomain(e: UserEntity): User =
        User(
            id = e.id,
            name = e.name,
            phone = e.phone,
            createdAt = e.createdAt,
        )

    fun toEntity(d: User): UserEntity =
        UserEntity(
            id = d.id,
            name = d.name,
            phone = d.phone,
            createdAt = d.createdAt,
        )
}

object PreferencesMapper {
    fun toDomain(e: PreferencesEntity): Preferences =
        Preferences(
            userId = e.userId,
            riskProfile = e.riskProfile,
            favoriteAssets = e.favoriteAssets.toList(),
        )

    fun toEntity(d: Preferences): PreferencesEntity =
        PreferencesEntity(
            userId = d.userId,
            riskProfile = d.riskProfile,
            favoriteAssets = d.favoriteAssets.toMutableList(),
        )
}

object PortfolioMapper {
    fun toDomain(e: PortfolioEntity): Portfolio =
        Portfolio(
            id = e.id,
            userId = e.userId,
            symbol = e.symbol,
            quantity = e.quantity,
            averagePrice = e.averagePrice,
        )

    fun toEntity(d: Portfolio): PortfolioEntity =
        PortfolioEntity(
            id = d.id,
            userId = d.userId,
            symbol = d.symbol,
            quantity = d.quantity,
            averagePrice = d.averagePrice,
        )
}

object AlertMapper {
    fun toDomain(e: AlertEntity): AlertConfig =
        AlertConfig(
            id = e.id,
            userId = e.userId,
            symbol = e.symbol,
            conditionType = e.conditionType,
            threshold = e.threshold,
        )

    fun toEntity(d: AlertConfig): AlertEntity =
        AlertEntity(
            id = d.id,
            userId = d.userId,
            symbol = d.symbol,
            conditionType = d.conditionType,
            threshold = d.threshold,
        )
}

package com.iip.userservice.application

import com.iip.userservice.application.dto.AlertItemResponse
import com.iip.userservice.application.dto.PortfolioItemResponse
import com.iip.userservice.application.dto.PreferencesResponse
import com.iip.userservice.application.dto.UserFullResponse
import com.iip.userservice.domain.model.AlertConfig
import com.iip.userservice.domain.model.Portfolio
import com.iip.userservice.domain.model.Preferences
import com.iip.userservice.domain.model.User

object UserResponseAssembler {
    fun toFullResponse(
        user: User,
        preferences: Preferences?,
        portfolio: List<Portfolio>,
        alerts: List<AlertConfig> = emptyList(),
    ): UserFullResponse {
        val id = user.id ?: error("user id required for response")
        return UserFullResponse(
            id = id,
            name = user.name,
            phone = user.phone,
            preferences = preferences?.let {
                PreferencesResponse(
                    riskProfile = it.riskProfile.name,
                    favoriteAssets = it.favoriteAssets,
                )
            },
            portfolio = portfolio.map {
                PortfolioItemResponse(
                    symbol = it.symbol,
                    quantity = it.quantity,
                    averagePrice = it.averagePrice,
                )
            },
            alerts = alerts.map {
                AlertItemResponse(
                    symbol = it.symbol,
                    conditionType = it.conditionType.name,
                    threshold = it.threshold,
                )
            },
        )
    }
}

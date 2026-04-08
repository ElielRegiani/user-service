package com.iip.userservice.domain.model

data class Preferences(
    val userId: Long,
    val riskProfile: RiskProfile,
    val favoriteAssets: List<String>,
)

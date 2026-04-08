package com.iip.userservice.application.dto

import com.iip.userservice.domain.model.RiskProfile
import jakarta.validation.constraints.NotNull

data class PreferencesRequest(
    @field:NotNull val riskProfile: RiskProfile,
    @field:NotNull val favoriteAssets: List<String>,
)

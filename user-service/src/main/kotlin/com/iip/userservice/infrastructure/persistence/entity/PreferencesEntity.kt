package com.iip.userservice.infrastructure.persistence.entity

import com.iip.userservice.domain.model.RiskProfile
import jakarta.persistence.CollectionTable
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Table

@Entity
@Table(name = "preferences")
class PreferencesEntity(
    @Id
    @Column(name = "user_id")
    var userId: Long = 0,

    @Enumerated(EnumType.STRING)
    @Column(name = "risk_profile", nullable = false, length = 20)
    var riskProfile: RiskProfile = RiskProfile.MEDIUM,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "preferences_favorite_assets",
        joinColumns = [JoinColumn(name = "user_id")],
    )
    @Column(name = "asset_symbol", nullable = false, length = 32)
    var favoriteAssets: MutableList<String> = mutableListOf(),
)

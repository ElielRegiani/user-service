package com.iip.userservice.application.usecase

import com.iip.userservice.application.UserResponseAssembler
import com.iip.userservice.application.dto.PreferencesRequest
import com.iip.userservice.application.dto.UserFullResponse
import com.iip.userservice.application.exception.UserNotFoundException
import com.iip.userservice.domain.model.Preferences
import com.iip.userservice.domain.repository.AlertRepository
import com.iip.userservice.domain.repository.PortfolioRepository
import com.iip.userservice.domain.repository.PreferencesRepository
import com.iip.userservice.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdatePreferencesUseCase(
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val portfolioRepository: PortfolioRepository,
    private val alertRepository: AlertRepository,
) {
    @Transactional
    fun execute(userId: Long, request: PreferencesRequest): UserFullResponse {
        val user = userRepository.findById(userId)
            ?: throw UserNotFoundException("User not found: $userId")
        val id = user.id ?: throw IllegalStateException("user without id")
        val saved = preferencesRepository.save(
            Preferences(
                userId = id,
                riskProfile = request.riskProfile,
                favoriteAssets = request.favoriteAssets,
            ),
        )
        val portfolio = portfolioRepository.findByUserId(id)
        val alerts = alertRepository.findByUserId(id)
        return UserResponseAssembler.toFullResponse(user, saved, portfolio, alerts)
    }
}

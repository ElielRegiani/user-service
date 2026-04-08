package com.iip.userservice.application.usecase

import com.iip.userservice.application.UserResponseAssembler
import com.iip.userservice.application.dto.UserFullResponse
import com.iip.userservice.application.exception.UserNotFoundException
import com.iip.userservice.domain.repository.AlertRepository
import com.iip.userservice.domain.repository.PortfolioRepository
import com.iip.userservice.domain.repository.PreferencesRepository
import com.iip.userservice.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GetUserByPhoneUseCase(
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val portfolioRepository: PortfolioRepository,
    private val alertRepository: AlertRepository,
) {
    @Transactional(readOnly = true)
    fun execute(phone: String): UserFullResponse {
        val user = userRepository.findByPhone(phone.trim())
            ?: throw UserNotFoundException("User not found for phone: $phone")
        val id = user.id ?: throw IllegalStateException("user without id")
        val preferences = preferencesRepository.findByUserId(id)
        val portfolio = portfolioRepository.findByUserId(id)
        val alerts = alertRepository.findByUserId(id)
        return UserResponseAssembler.toFullResponse(user, preferences, portfolio, alerts)
    }
}

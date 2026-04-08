package com.iip.userservice.application.usecase

import com.iip.userservice.application.UserResponseAssembler
import com.iip.userservice.application.dto.PortfolioRequest
import com.iip.userservice.application.dto.UserFullResponse
import com.iip.userservice.application.exception.UserNotFoundException
import com.iip.userservice.domain.model.Portfolio
import com.iip.userservice.domain.repository.AlertRepository
import com.iip.userservice.domain.repository.PortfolioRepository
import com.iip.userservice.domain.repository.PreferencesRepository
import com.iip.userservice.domain.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddOrUpdatePortfolioUseCase(
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val portfolioRepository: PortfolioRepository,
    private val alertRepository: AlertRepository,
) {
    @Transactional
    fun execute(userId: Long, request: PortfolioRequest): UserFullResponse {
        val user = userRepository.findById(userId)
            ?: throw UserNotFoundException("User not found: $userId")
        val id = user.id ?: throw IllegalStateException("user without id")
        val symbol = request.symbol.trim().uppercase()
        val existing = portfolioRepository.findByUserIdAndSymbol(id, symbol)
        val toSave =
            if (existing != null) {
                existing.copy(
                    quantity = request.quantity,
                    averagePrice = request.averagePrice,
                )
            } else {
                Portfolio(
                    userId = id,
                    symbol = symbol,
                    quantity = request.quantity,
                    averagePrice = request.averagePrice,
                )
            }
        portfolioRepository.save(toSave)
        val preferences = preferencesRepository.findByUserId(id)
        val portfolio = portfolioRepository.findByUserId(id)
        val alerts = alertRepository.findByUserId(id)
        return UserResponseAssembler.toFullResponse(user, preferences, portfolio, alerts)
    }
}

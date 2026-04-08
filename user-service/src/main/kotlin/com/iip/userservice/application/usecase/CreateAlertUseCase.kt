package com.iip.userservice.application.usecase

import com.iip.userservice.application.UserResponseAssembler
import com.iip.userservice.application.dto.AlertRequest
import com.iip.userservice.application.dto.UserFullResponse
import com.iip.userservice.application.exception.DuplicateAlertException
import com.iip.userservice.application.exception.UserNotFoundException
import com.iip.userservice.domain.model.AlertConfig
import com.iip.userservice.domain.repository.AlertRepository
import com.iip.userservice.domain.repository.PortfolioRepository
import com.iip.userservice.domain.repository.PreferencesRepository
import com.iip.userservice.domain.repository.UserRepository
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateAlertUseCase(
    private val userRepository: UserRepository,
    private val preferencesRepository: PreferencesRepository,
    private val portfolioRepository: PortfolioRepository,
    private val alertRepository: AlertRepository,
    meterRegistry: MeterRegistry,
) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val alertsCreated = meterRegistry.counter("alerts.created")

    @Transactional
    fun execute(userId: Long, request: AlertRequest): UserFullResponse {
        val user = userRepository.findById(userId)
            ?: throw UserNotFoundException("User not found: $userId")
        val id = user.id ?: throw IllegalStateException("user without id")
        val symbol = request.symbol.trim().uppercase()
        if (
            alertRepository.existsByUserIdAndSymbolAndConditionType(
                id,
                symbol,
                request.conditionType,
            )
        ) {
            throw DuplicateAlertException(
                "Alert already exists for user=$id symbol=$symbol condition=${request.conditionType}",
            )
        }
        alertRepository.save(
            AlertConfig(
                userId = id,
                symbol = symbol,
                conditionType = request.conditionType,
                threshold = request.threshold,
            ),
        )
        alertsCreated.increment()
        log.info(
            "event=alert_created userId={} symbol={} condition={}",
            id,
            symbol,
            request.conditionType,
        )
        val preferences = preferencesRepository.findByUserId(id)
        val portfolio = portfolioRepository.findByUserId(id)
        val alerts = alertRepository.findByUserId(id)
        return UserResponseAssembler.toFullResponse(user, preferences, portfolio, alerts)
    }
}

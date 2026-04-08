package com.iip.userservice.application.usecase

import com.iip.userservice.application.UserResponseAssembler
import com.iip.userservice.application.dto.CreateUserRequest
import com.iip.userservice.application.dto.UserFullResponse
import com.iip.userservice.application.exception.DuplicatePhoneException
import com.iip.userservice.domain.model.User
import com.iip.userservice.domain.repository.UserRepository
import io.micrometer.core.instrument.MeterRegistry
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
class CreateUserUseCase(
    private val userRepository: UserRepository,
    meterRegistry: MeterRegistry,
) {
    private val log = LoggerFactory.getLogger(javaClass)
    private val usersCreated = meterRegistry.counter("users.created")

    @Transactional
    fun execute(request: CreateUserRequest): UserFullResponse {
        val phone = request.phone.trim()
        if (userRepository.existsByPhone(phone)) {
            throw DuplicatePhoneException("Phone already registered: $phone")
        }
        val user = userRepository.save(
            User(
                name = request.name.trim(),
                phone = phone,
                createdAt = Instant.now(),
            ),
        )
        usersCreated.increment()
        log.info("event=user_created userId={} phone={}", user.id, phone)
        return UserResponseAssembler.toFullResponse(user, null, emptyList(), emptyList())
    }
}

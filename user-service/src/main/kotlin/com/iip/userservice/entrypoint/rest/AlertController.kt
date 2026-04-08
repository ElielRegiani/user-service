package com.iip.userservice.entrypoint.rest

import com.iip.userservice.application.dto.AlertRequest
import com.iip.userservice.application.usecase.CreateAlertUseCase
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AlertController(
    private val createAlertUseCase: CreateAlertUseCase,
) {
    @PostMapping("/users/{userId}/alerts")
    fun create(
        @PathVariable userId: Long,
        @Valid @RequestBody body: AlertRequest,
    ) = createAlertUseCase.execute(userId, body)
}

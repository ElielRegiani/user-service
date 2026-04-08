package com.iip.userservice.entrypoint.rest

import com.iip.userservice.application.dto.PortfolioRequest
import com.iip.userservice.application.usecase.AddOrUpdatePortfolioUseCase
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PortfolioController(
    private val addOrUpdatePortfolioUseCase: AddOrUpdatePortfolioUseCase,
) {
    @PostMapping("/users/{userId}/portfolio")
    fun addOrUpdate(
        @PathVariable userId: Long,
        @Valid @RequestBody body: PortfolioRequest,
    ) = addOrUpdatePortfolioUseCase.execute(userId, body)
}

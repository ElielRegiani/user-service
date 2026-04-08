package com.iip.userservice.entrypoint.rest

import com.iip.userservice.application.dto.CreateUserRequest
import com.iip.userservice.application.dto.PreferencesRequest
import com.iip.userservice.application.usecase.CreateUserUseCase
import com.iip.userservice.application.usecase.GetUserByPhoneUseCase
import com.iip.userservice.application.usecase.UpdatePreferencesUseCase
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserByPhoneUseCase: GetUserByPhoneUseCase,
    private val updatePreferencesUseCase: UpdatePreferencesUseCase,
) {
    @PostMapping
    fun create(
        @Valid @RequestBody body: CreateUserRequest,
    ) = createUserUseCase.execute(body)

    @GetMapping("/phone/{phone}")
    fun getByPhone(
        @PathVariable phone: String,
    ) = getUserByPhoneUseCase.execute(phone)

    @PostMapping("/{userId}/preferences")
    fun updatePreferences(
        @PathVariable userId: Long,
        @Valid @RequestBody body: PreferencesRequest,
    ) = updatePreferencesUseCase.execute(userId, body)
}

package com.iip.userservice.application.usecase

import com.iip.userservice.application.dto.CreateUserRequest
import com.iip.userservice.application.exception.DuplicatePhoneException
import com.iip.userservice.domain.model.User
import com.iip.userservice.domain.repository.UserRepository
import io.micrometer.core.instrument.simple.SimpleMeterRegistry
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class CreateUserUseCaseTest {
    private val userRepository: UserRepository = mock()
    private val useCase = CreateUserUseCase(userRepository, SimpleMeterRegistry())

    @Test
    fun `creates user when phone is new`() {
        whenever(userRepository.existsByPhone("5511999999999")).thenReturn(false)
        whenever(userRepository.save(any())).thenAnswer { inv ->
            val u = inv.getArgument<User>(0)
            u.copy(id = 1L)
        }

        val res =
            useCase.execute(
                CreateUserRequest(name = "Eliel", phone = "5511999999999"),
            )

        assertEquals(1L, res.id)
        assertEquals("Eliel", res.name)
        assertEquals("5511999999999", res.phone)
        verify(userRepository).save(any())
    }

    @Test
    fun `rejects duplicate phone`() {
        whenever(userRepository.existsByPhone("5511999999999")).thenReturn(true)

        assertThrows(DuplicatePhoneException::class.java) {
            useCase.execute(CreateUserRequest(name = "Eliel", phone = "5511999999999"))
        }
    }
}

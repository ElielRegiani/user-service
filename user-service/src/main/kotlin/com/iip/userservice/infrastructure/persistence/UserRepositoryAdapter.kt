package com.iip.userservice.infrastructure.persistence

import com.iip.userservice.domain.model.User
import com.iip.userservice.domain.repository.UserRepository
import com.iip.userservice.infrastructure.persistence.mapper.UserMapper
import com.iip.userservice.infrastructure.persistence.repository.UserJpaRepository
import org.springframework.stereotype.Component

@Component
class UserRepositoryAdapter(
    private val jpa: UserJpaRepository,
) : UserRepository {
    override fun save(user: User): User {
        val saved = jpa.save(UserMapper.toEntity(user))
        return UserMapper.toDomain(saved)
    }

    override fun findById(id: Long): User? =
        jpa.findById(id).map(UserMapper::toDomain).orElse(null)

    override fun findByPhone(phone: String): User? =
        jpa.findByPhone(phone).map(UserMapper::toDomain).orElse(null)

    override fun existsByPhone(phone: String): Boolean =
        jpa.existsByPhone(phone)
}

package com.iip.userservice.infrastructure.persistence.repository

import com.iip.userservice.infrastructure.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UserJpaRepository : JpaRepository<UserEntity, Long> {
    fun findByPhone(phone: String): Optional<UserEntity>

    fun existsByPhone(phone: String): Boolean
}

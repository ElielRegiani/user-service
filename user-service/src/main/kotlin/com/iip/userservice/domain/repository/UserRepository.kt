package com.iip.userservice.domain.repository

import com.iip.userservice.domain.model.User

interface UserRepository {
    fun save(user: User): User
    fun findById(id: Long): User?
    fun findByPhone(phone: String): User?
    fun existsByPhone(phone: String): Boolean
}

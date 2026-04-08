package com.iip.userservice.domain.model

import java.time.Instant

data class User(
    val id: Long? = null,
    val name: String,
    val phone: String,
    val createdAt: Instant,
)

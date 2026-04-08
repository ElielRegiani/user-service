package com.iip.userservice.infrastructure.persistence.repository

import com.iip.userservice.infrastructure.persistence.entity.PreferencesEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PreferencesJpaRepository : JpaRepository<PreferencesEntity, Long>

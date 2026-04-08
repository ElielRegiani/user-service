package com.iip.userservice.infrastructure.exception

import com.iip.userservice.application.exception.DuplicateAlertException
import com.iip.userservice.application.exception.DuplicatePhoneException
import com.iip.userservice.application.exception.UserNotFoundException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ErrorBody(
    val error: String,
    val message: String? = null,
)

@RestControllerAdvice
class RestExceptionHandler {
    @ExceptionHandler(UserNotFoundException::class)
    fun notFound(ex: UserNotFoundException): ResponseEntity<ErrorBody> =
        ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorBody("not_found", ex.message))

    @ExceptionHandler(DuplicatePhoneException::class, DuplicateAlertException::class)
    fun conflict(ex: RuntimeException): ResponseEntity<ErrorBody> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorBody("conflict", ex.message))

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun dataIntegrity(ex: DataIntegrityViolationException): ResponseEntity<ErrorBody> =
        ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorBody("conflict", ex.mostSpecificCause.message))

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validation(ex: MethodArgumentNotValidException): ResponseEntity<ErrorBody> {
        val msg =
            ex.bindingResult.fieldErrors.joinToString("; ") { "${it.field}: ${it.defaultMessage}" }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorBody("validation_error", msg))
    }
}

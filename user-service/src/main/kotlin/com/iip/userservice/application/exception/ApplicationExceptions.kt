package com.iip.userservice.application.exception

class UserNotFoundException(message: String) : RuntimeException(message)

class DuplicatePhoneException(message: String) : RuntimeException(message)

class DuplicateAlertException(message: String) : RuntimeException(message)

package com.example.demo.common.exception

import com.example.demo.common.exception.dto.GlobalExceptionResponse
import com.example.demo.common.security.SecurityException
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.resource.NoResourceFoundException

private val logger = KotlinLogging.logger {}

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException): ResponseEntity<GlobalExceptionResponse> {
        val body = GlobalExceptionResponse(code = e.code, message = e.message)
        return ResponseEntity.status(e.status).body(body)
    }

    @ExceptionHandler(SecurityException::class)
    fun handleAuthenticationException(e: SecurityException): ResponseEntity<GlobalExceptionResponse> {
        val body = GlobalExceptionResponse(code = "SECURITY_AUTHENTICATION_FAILED", message = e.message)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body)
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handleMethodArgumentTypeMismatchException(e: MethodArgumentTypeMismatchException): ResponseEntity<GlobalExceptionResponse> {
        val errorCode = BaseErrorCode.METHOD_ARGUMENT_TYPE_MISMATCH
        val body = GlobalExceptionResponse(code = errorCode.code, message = errorCode.message)
        return ResponseEntity.status(errorCode.status).body(body)
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleHttpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException): ResponseEntity<GlobalExceptionResponse> {
        val errorCode = BaseErrorCode.METHOD_NOT_SUPPORT
        val body = GlobalExceptionResponse(code = errorCode.code, message = errorCode.message)
        return ResponseEntity.status(errorCode.status).body(body)
    }

    @ExceptionHandler(NoResourceFoundException::class)
    fun handleNoResourceFoundException(e: NoResourceFoundException): ResponseEntity<GlobalExceptionResponse> {
        val errorCode = BaseErrorCode.NO_RESOURCE
        val body = GlobalExceptionResponse(code = errorCode.code, message = errorCode.message)
        return ResponseEntity.status(errorCode.status).body(body)
    }

    @ExceptionHandler(Exception::class)
    fun handleUnCatchException(e: Exception): ResponseEntity<GlobalExceptionResponse> {
        logger.error { "GlobalExceptionHandler::handleUnCatchException\n| >> EXCEPTION_TYPE: ${e::class}\n| >> EXCEPTION_MESSAGE: ${e.message}" }

        val errorCode = BaseErrorCode.UNKNOWN
        val body = GlobalExceptionResponse(code = errorCode.code, message = errorCode.message)
        return ResponseEntity.status(errorCode.status).body(body)
    }
}

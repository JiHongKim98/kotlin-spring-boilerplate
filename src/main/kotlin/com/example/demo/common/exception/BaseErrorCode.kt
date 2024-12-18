package com.example.demo.common.exception

import org.springframework.http.HttpStatus

enum class BaseErrorCode(val code: String, val message: String, val status: HttpStatus) {
    UNKNOWN("UN_KNOWN_ERROR", "not included in any exception class", HttpStatus.INTERNAL_SERVER_ERROR),
    METHOD_ARGUMENT_TYPE_MISMATCH("METHOD_ARGUMENT_TYPE_MISMATCH", "method argument error", HttpStatus.BAD_REQUEST),
    METHOD_NOT_SUPPORT("METHOD_NOT_SUPPORTED", "method not supported", HttpStatus.BAD_REQUEST),
    NO_RESOURCE("NO_RESOURCE", "no such resource", HttpStatus.NOT_FOUND),
    ;
}

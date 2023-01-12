package com.jhouse.kotlinrestfulapi.controller

import com.jhouse.kotlinrestfulapi.exceptions.NotFoundException
import com.jhouse.kotlinrestfulapi.exceptions.UnauthorizeException
import com.jhouse.kotlinrestfulapi.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(constraintViolationException: ConstraintViolationException):WebResponse<String>{
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message!!
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFoundHandler(notFoundException: NotFoundException):WebResponse<String>{
        return WebResponse(
            code = 404,
            status = "NOT FOUND",
            data = "Not Found"
        )
    }

    @ExceptionHandler(value = [UnauthorizeException::class])
    fun unauthorizeHandler(unauthorizeException: UnauthorizeException):WebResponse<String>{
        return WebResponse(
            code = 401,
            status = "UNAUTHORIZED",
            data = "Please put your X-Api-Key"
        )
    }
}
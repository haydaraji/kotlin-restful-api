package com.jhouse.kotlinrestfulapi.auth

import com.jhouse.kotlinrestfulapi.exceptions.UnauthorizeException
import com.jhouse.kotlinrestfulapi.repository.ApiKeyRepository
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor
import java.lang.Exception

@Component
class ApiKeyInterceptor(val apiKeyRepository: ApiKeyRepository) : WebRequestInterceptor {
    override fun preHandle(request: WebRequest) {
        val apiKey = request.getHeader("X-Api-Key")
        if (apiKey == null) {
            throw UnauthorizeException()
        } else {
            if (!apiKeyRepository.existsById(apiKey)) {
                throw UnauthorizeException()
            }
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        //TODO("Not yet implemented")
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        //TODO("Not yet implemented")
    }
}
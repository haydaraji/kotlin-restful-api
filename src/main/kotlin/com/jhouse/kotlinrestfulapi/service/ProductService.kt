package com.jhouse.kotlinrestfulapi.service

import com.jhouse.kotlinrestfulapi.model.CreateProductRequest
import com.jhouse.kotlinrestfulapi.model.ListProductRequest
import com.jhouse.kotlinrestfulapi.model.ProductResponse
import com.jhouse.kotlinrestfulapi.model.UpdateProductRequest

interface ProductService {

    fun create(createProductRequest: CreateProductRequest): ProductResponse
    fun get(id : String): ProductResponse
    fun update(id : String, updateProductRequest: UpdateProductRequest): ProductResponse
    fun delete(id : String)
    fun getAll(listProductRequest: ListProductRequest): List<ProductResponse>
}
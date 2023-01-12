package com.jhouse.kotlinrestfulapi.service.impl

import com.jhouse.kotlinrestfulapi.entity.Product
import com.jhouse.kotlinrestfulapi.exceptions.NotFoundException
import com.jhouse.kotlinrestfulapi.model.CreateProductRequest
import com.jhouse.kotlinrestfulapi.model.ListProductRequest
import com.jhouse.kotlinrestfulapi.model.ProductResponse
import com.jhouse.kotlinrestfulapi.model.UpdateProductRequest
import com.jhouse.kotlinrestfulapi.repository.ProductRepository
import com.jhouse.kotlinrestfulapi.service.ProductService
import com.jhouse.kotlinrestfulapi.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.awt.print.Pageable
import java.util.*
import java.util.stream.Collector
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)

        val product = Product (
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!!,
            createdAt = Date(),
            modifedAt = null
       )
        productRepository.save(product)
        return convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)
        return convertProductToProductResponse(product)
    }

    override fun update(id : String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)

        validationUtil.validate(updateProductRequest)
        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            modifedAt = Date() }
        productRepository.save(product)
        return convertProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductByIdOrThrowNotFound(id)
        productRepository.deleteById(id)
    }

    override fun getAll(listProductRequest: ListProductRequest): List<ProductResponse> {
        val pageable = PageRequest.of(listProductRequest.page, listProductRequest.size)
        val page = productRepository.findAll(pageable)
        val products : List<Product> = page.get().collect(Collectors.toList())

        return products.map { convertProductToProductResponse(it) }
    }

    private fun findProductByIdOrThrowNotFound(id : String) : Product {
        val product = productRepository.findByIdOrNull(id);
        if (product == null) {
            throw NotFoundException()
        } else {
            return product
        }
    }

    private fun convertProductToProductResponse(product: Product) : ProductResponse{
        return ProductResponse(
            id = product.id,
            name = product.name,
            price = product.price,
            quantity = product.quantity,
            createdAt = product.createdAt,
            modifedAt = product.modifedAt
        )
    }
}
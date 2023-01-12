package com.jhouse.kotlinrestfulapi.controller

import com.jhouse.kotlinrestfulapi.model.*
import com.jhouse.kotlinrestfulapi.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val productService: ProductService) {

    @PostMapping(
        value = ["/api/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody productRequest: CreateProductRequest) : WebResponse<ProductResponse> {
       val productResponse = productService.create(productRequest)

        return WebResponse(
            code = 200,
            status = "SUCCESS",
            data = productResponse
        )
    }

    @GetMapping(
        value = ["/api/products/{id_product}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("id_product") id: String) : WebResponse<ProductResponse> {
       val productResponse = productService.get(id)

        return WebResponse(
            code = 200,
            status = "SUCCESS",
            data = productResponse
        )
    }

    @PutMapping(
        value = ["/api/products/{id_product}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateProduct(@PathVariable("id_product") id: String, @RequestBody productRequest: UpdateProductRequest) : WebResponse<ProductResponse> {
        val productResponse = productService.update(id, productRequest)

        return WebResponse(
            code = 200,
            status = "SUCCESS",
            data = productResponse
        )
    }

    @DeleteMapping(
        value = ["/api/products/{id_product}"],
        produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("id_product") id: String) : WebResponse<String> {
        productService.delete(id);
        return WebResponse(
            code = 200,
            status = "SUCCESS",
            data = id
        )
    }

    @GetMapping(
        value = ["/api/products"],
        produces = ["application/json"]
    )
    fun getAllProduct(@RequestParam("page", defaultValue = "0") page: Int,
                      @RequestParam("size", defaultValue = "10") size: Int) : WebResponse<List<ProductResponse>> {
        val listProductRequest = ListProductRequest(page=page, size=size)
        val productResponse = productService.getAll(listProductRequest)

        return WebResponse(
            code = 200,
            status = "SUCCESS",
            data = productResponse
        )
    }
}
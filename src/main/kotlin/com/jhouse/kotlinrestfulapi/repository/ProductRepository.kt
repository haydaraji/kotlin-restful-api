package com.jhouse.kotlinrestfulapi.repository

import com.jhouse.kotlinrestfulapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {
}
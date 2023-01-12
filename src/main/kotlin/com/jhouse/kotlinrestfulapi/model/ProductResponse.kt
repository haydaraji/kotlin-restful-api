package com.jhouse.kotlinrestfulapi.model

import java.util.*

data class ProductResponse (
    val id: String,
    val name: String,
    val price: Long,
    val quantity: Long,
    val createdAt: Date,
    val modifedAt: Date?
)
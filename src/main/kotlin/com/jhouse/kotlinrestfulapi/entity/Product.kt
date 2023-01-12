package com.jhouse.kotlinrestfulapi.entity

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "products")
data class Product (
    @Id
    val id: String,

    @Column(name = "name")
    var name: String,

    @Column(name = "price")
    var price: Long,

    @Column(name = "quantity")
    var quantity: Long,

    @Column(name = "created_at")
    val createdAt: Date,

    @Column(name = "modifed_at")
    var modifedAt: Date?
)
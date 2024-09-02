package com.shyam.graphql.entities

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Int? = null,
    val orderDetail: String,
    val address: String,
    val price: Int
) {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    var user: User? = null

    constructor(orderDetail: String, address: String, price: Int) : this(
        null, orderDetail, address, price
    )
}

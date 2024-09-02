package com.shyam.graphql.response

data class OrderResponse(
    val orderId: Int,
    val orderDetail: String,
    val address: String,
    val price: Int,
    val user: UserResponse?
)

package com.shyam.graphql.response

data class UserResponse(
    val userId: Int,
    val name: String,
    val phone: String,
    val email: String,
    val password: String,
    val orderList: List<OrderResponse>?
)

package com.shyam.graphql.controller

import com.shyam.graphql.entities.Order
import com.shyam.graphql.services.OrderService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import com.shyam.graphql.response.OrderResponse
import com.shyam.graphql.services.UserService

@Controller
class OrderController(val orderService: OrderService, val userService: UserService) {

    @MutationMapping
    fun createOrder(
        @Argument orderDetail: String,
        @Argument address: String,
        @Argument price: Int,
        @Argument userId: Int
    ): OrderResponse {
        val user = userService.getUserEntity(userId)  // Retrieve the User entity
        val order = Order(orderDetail, address, price).apply {
            this.user = user  // Assign the User entity to the order
        }
        return orderService.createOrder(order)
    }

    @MutationMapping
    fun deleteOrder(@Argument orderId: Int): Boolean {
        return orderService.deleteOrder(orderId)
    }

    @QueryMapping
    fun getAllOrder(): List<OrderResponse> {
        return orderService.getAllOrder()
    }

    @QueryMapping
    fun getOrder(@Argument orderId: Int): OrderResponse {
        return orderService.getOrder(orderId)
    }
}

package com.shyam.graphql.services

import com.shyam.graphql.entities.Order
import com.shyam.graphql.repositories.OrderRepository
import org.springframework.stereotype.Service
import com.shyam.graphql.response.OrderResponse
import com.shyam.graphql.response.UserResponse

@Service
class OrderService(private val orderRepository: OrderRepository) {

    fun createOrder(order: Order): OrderResponse {
        val savedOrder = orderRepository.save(order)
        return mapToOrderResponse(savedOrder)
    }

    fun getAllOrder(): List<OrderResponse> {
        return orderRepository.findAll().map { mapToOrderResponse(it) }
    }

    fun getOrder(id: Int): OrderResponse {
        val order = orderRepository.findById(id).orElseThrow { RuntimeException("Order not found !!") }
        return mapToOrderResponse(order)
    }

    fun deleteOrder(id: Int): Boolean {
        val order = orderRepository.findById(id)
        if (order.isEmpty) return false
        orderRepository.deleteById(id)
        return true
    }

    private fun mapToOrderResponse(order: Order): OrderResponse {
        return OrderResponse(
            orderId = order.orderId ?: 0,
            orderDetail = order.orderDetail,
            address = order.address,
            price = order.price,
            user = order.user?.let { user ->
                UserResponse(
                    userId = user.userId ?: 0,
                    name = user.name,
                    phone = user.phone,
                    email = user.email,
                    password = user.password,
                    orderList = emptyList()
                )
            }
        )
    }
}

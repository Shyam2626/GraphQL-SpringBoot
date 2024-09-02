package com.shyam.graphql.services

import com.shyam.graphql.entities.User
import com.shyam.graphql.repositories.UserRepository
import org.springframework.stereotype.Service
import com.shyam.graphql.response.OrderResponse
import com.shyam.graphql.response.UserResponse

@Service
class UserService(val userRepository: UserRepository) {

    fun getUserEntity(id: Int): User {
        return userRepository.findById(id).orElseThrow {
            RuntimeException("User not found !!")
        }
    }

    fun createUser(user: User): UserResponse {
        val savedUser = userRepository.save(user)
        return mapToUserResponse(savedUser)
    }

    fun getAllUser(): List<UserResponse> {
        return userRepository.findAll().map { mapToUserResponse(it) }
    }

    fun getUser(id: Int): UserResponse {
        val user = getUserEntity(id)
        return mapToUserResponse(user)
    }

    fun deleteUser(id: Int): Boolean {
        val user = userRepository.findById(id)
        if (user.isEmpty) return false
        userRepository.deleteById(id)
        return true
    }

    // Utility function to map User entity to UserResponse
    private fun mapToUserResponse(user: User): UserResponse {
        return UserResponse(
            userId = user.userId ?: 0,
            name = user.name,
            phone = user.phone,
            email = user.email,
            password = user.password, // Include password if needed
            orderList = user.orderList.map { order ->
                OrderResponse(
                    orderId = order.orderId ?: 0,
                    orderDetail = order.orderDetail,
                    address = order.address,
                    price = order.price,
                    user = null // Set to null to avoid circular reference
                )
            }
        )
    }
}

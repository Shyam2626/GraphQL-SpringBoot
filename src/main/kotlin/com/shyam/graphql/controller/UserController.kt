package com.shyam.graphql.controller

import com.shyam.graphql.entities.User
import com.shyam.graphql.services.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import com.shyam.graphql.response.UserResponse

@Controller
class UserController(val userService: UserService) {

    @QueryMapping
    fun getAllUser(): List<UserResponse> {
        return userService.getAllUser()
    }

    @QueryMapping
    fun getUser(@Argument userId: Int): UserResponse {
        return userService.getUser(userId)
    }

    @MutationMapping
    fun createUser(@Argument name: String, @Argument phone: String, @Argument email: String, @Argument password: String): UserResponse {
        val user = User(name, phone, email, password)
        return userService.createUser(user)
    }

    @MutationMapping
    fun deleteUser(@Argument userId: Int): Boolean {
        return userService.deleteUser(userId)
    }
}

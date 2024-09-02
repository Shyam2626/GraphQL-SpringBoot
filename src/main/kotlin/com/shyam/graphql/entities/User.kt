package com.shyam.graphql.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Int? = null,
    val name: String,
    val phone: String,
    val email: String,
    val password: String
) {

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonBackReference
    var orderList: MutableList<Order> = mutableListOf()

    constructor(name: String, phone: String, email: String, password: String) : this(
        null, name, phone, email, password
    )

    fun addOrder(order: Order) {
        order.user = this
        orderList.add(order)
    }
}

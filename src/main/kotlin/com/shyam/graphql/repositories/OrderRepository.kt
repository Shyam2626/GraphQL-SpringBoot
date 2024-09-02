package com.shyam.graphql.repositories
import com.shyam.graphql.entities.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository : JpaRepository<Order, Int>
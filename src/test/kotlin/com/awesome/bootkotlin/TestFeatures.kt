package com.awesome.bootkotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import java.time.LocalDate

class TestFeatures {

    @Test
    fun dataClass() {
        val customer = Customer("Miew", joinDate = LocalDate.now())
        val newName = "Miew miew"
        customer.firstName = newName

        assertEquals(newName, customer.firstName)

    }

    @Test
    fun defaultParam() {
        val customer = Customer("Miew", joinDate = LocalDate.now())
        assertEquals(Category.NEW, customer.category)
    }

    @Test
    fun trueValueEquality(){
        val customer = Customer("Miew", joinDate = LocalDate.now())
        val copy = customer.copy()

        assert(customer.equals(copy))
        assert(customer == copy) // true value equality
    }

    @Test
    fun whenStmt() {
        val customer = Customer("Miew", joinDate = LocalDate.now())

        val discount: (c: Customer) -> Int = {
            when (customer.category) {
                Category.NEW -> 0
                Category.SILVER -> 5
                Category.GOLD -> 10
            }
        }
        assertEquals(0, discount(customer))
    }

    @Test
    fun nullRef(){
        val customer = Customer("Miew", joinDate = LocalDate.now())
        // customer.firstName = null // not allowed

        val findCustomer: Customer? = null
        val newCategory = findCustomer?.upgrade() // upgrade won't be called
        assertNull(newCategory)

        val findAnotherCustomer: Customer? = customer
        val anotherNewCategory = findAnotherCustomer?.upgrade()
        assertEquals(Category.SILVER, anotherNewCategory)

    }

}

data class Customer(var firstName: String, val category: Category = Category.NEW, val joinDate: LocalDate) {

    fun upgrade(): Category {
        return when (this.category) {
            Category.NEW -> Category.SILVER
            Category.SILVER -> Category.GOLD
            Category.GOLD -> Category.GOLD
        }
    }
}
enum class Category {
    NEW, SILVER, GOLD;
}

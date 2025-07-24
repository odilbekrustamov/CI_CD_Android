package uz.example.cicd

import org.junit.Assert.assertEquals
import org.junit.Test

class MainActivityTest {
    @Test
    fun testGreeting() {
        val result = getGreeting()
        assertEquals("Hello CI/CD", result)
    }
}
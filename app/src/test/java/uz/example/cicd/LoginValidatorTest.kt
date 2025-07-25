package uz.example.cicd

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class LoginValidatorTest {

    @Test
    fun `email with @ is valid`() {
        assertTrue(LoginValidator.isEmailValid("hello@gmail.com"))
    }

    @Test
    fun `email without @ is invalid`() {
        assertFalse(LoginValidator.isEmailValid("hellogmail.com"))
    }

    @Test
    fun `password longer than 6 is valid`() {
        assertTrue(LoginValidator.isPasswordValid("123456"))
    }

    @Test
    fun `password shorter than 6 is invalid`() {
        assertFalse(LoginValidator.isPasswordValid("123"))
    }
}

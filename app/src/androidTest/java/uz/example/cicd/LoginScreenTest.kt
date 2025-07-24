package uz.example.cicd

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            LoginScreen(
                onLoginClicked = { _, _ -> }
            )
        }
    }

    @Test
    fun loginScreen_shows_all_elements() {
        composeTestRule.onNodeWithTag("header").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithTag("emailField").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithTag("passwordField").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithTag("loginButton").assertExists().assertIsDisplayed()
        composeTestRule.onNodeWithTag("loginButton").performClick()
    }

    @Test
    fun loginScreen_enters_text_correctly() {
        composeTestRule.onNodeWithTag("emailField").performTextInput("test@gmail.com")
        composeTestRule.onNodeWithTag("passwordField").performTextInput("password")
        composeTestRule.onNodeWithTag("loginButton").performClick()
    }
}

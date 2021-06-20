package test

import com.ktorpostgresample.utility.Utility
import di.notepadModule
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject


class UtilityTest : KoinTest{

    val mockUtility by inject<Utility>()

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        printLogger()
        modules(notepadModule)
    }

    @Test
    fun `if checkNumber() return true`() {

        assertTrue(mockUtility.checkNumber("3"));
    }

    @Test
    fun `if checkNumber() return false`() {

        assertFalse(mockUtility.checkNumber("abc"));

    }



}
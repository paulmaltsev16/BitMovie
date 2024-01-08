package com.paulmaltsev.bitmovie.core.core.exntensions;

import com.google.gson.Gson
import com.paulmaltsev.bitmovie.core.extensions.fromJson
import com.paulmaltsev.bitmovie.core.extensions.toJson
import junit.framework.TestCase
import org.junit.Test

data class User(
    val name: String
)

class JsonExtensionTest {
    @Test
    fun `toJson() method should converts list of strings to json`() {
        // Arrange
        val inputList = listOf("one", "two", "free")

        // Act
        val json = inputList.toJson()

        // Assert
        val expectedJson = Gson().toJson(inputList)
        TestCase.assertEquals(expectedJson, json)
    }

    @Test
    fun `fromJson() method should return array list of object`() {
        // Arrange
        val mockJsonString = "[{\"name\":\"Alice\"}, {\"name\":\"Bob\"}]"
        val expectedResult = arrayListOf(
            User("Alice"),
            User("Bob")
        )

        // Act
        val result = fromJson<User>(mockJsonString)

        // Assert
        TestCase.assertEquals(expectedResult, result)
    }
}
package com.github.abvdasker.sudokutest

import kotlin.test.Test

import com.github.abvdasker.sudoku.BoardInputValidator
import com.github.abvdasker.sudoku.errors.InvalidInputException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BoardInputValidatorTest {
    @Test fun testValidate_noRows() {
        val input = ArrayList<ArrayList<Int?>>()

        assertFailsWith(InvalidInputException::class, "") {
            BoardInputValidator(input).validate()
        }
    }

    @Test fun testValidate_tooManyRows() {
        val input = ArrayList<ArrayList<Int?>>()
        for (i in 0 until 10) {
            input.add(ArrayList())
        }

        assertFailsWith(InvalidInputException::class, "") {
            BoardInputValidator(input).validate()
        }
    }

    @Test fun testValidate_notEnoughColumns() {
        val input = ArrayList<ArrayList<Int?>>()
        for (i in 0 until 9) {
            input.add(ArrayList())
        }

        assertFailsWith(InvalidInputException::class, "") {
            BoardInputValidator(input).validate()
        }
    }

    @Test fun testValidate_tooManyColumns() {
        val input = ArrayList<ArrayList<Int?>>()
        for (i in 0 until 9) {
            val row = ArrayList<Int?>()
            for (j in 0 until 10) {
                row.add(null)
            }
            input.add(row)
        }

        assertFailsWith(InvalidInputException::class, "") {
            BoardInputValidator(input).validate()
        }
    }

    @Test fun testValidate() {
        val input = ArrayList<ArrayList<Int>>()
        for (i in 0 until 9) {
            val row = ArrayList<Int>()
            for (j in 0 until 9) {
                row.add(0)
            }
            input.add(row)
        }

        val validated = BoardInputValidator(input).validate()

        assertEquals(validated, input)
    }
}
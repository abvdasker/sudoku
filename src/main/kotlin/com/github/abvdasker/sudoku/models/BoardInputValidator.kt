package com.github.abvdasker.sudoku.models

import com.github.abvdasker.sudoku.errors.InvalidInputException

class BoardInputValidator(val input: List<List<Int?>>) {
    companion object {
        const val ROWS = 9
        const val COLUMNS = 9
    }

    fun validate(): List<List<Int>> {
        validateRows()

        val validatedInput = ArrayList<ArrayList<Int>>()
        input.forEach { row ->
            validateCols(row)

            val validatedRow = ArrayList<Int>()
            row.forEach { value ->
                val correctedValue = value ?: 0
                validatedRow.add(correctedValue)
            }
            validatedInput.add(validatedRow)
        }
        return validatedInput
    }

    private fun validateRows() {
        if (input.size > ROWS) {
            throw InvalidInputException("board has more than 9 rows")
        }
        if (input.size < ROWS) {
            throw InvalidInputException("board has fewer than 9 rows")
        }
    }

    private fun validateCols(row: List<Int?>) {
        if (row.size > COLUMNS) {
            throw InvalidInputException("board has more than 9 columns")
        }
        if (row.size < COLUMNS) {
            throw InvalidInputException("board has fewer than than 9 columns")
        }
    }
}
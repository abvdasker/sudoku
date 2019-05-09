package com.github.abvdasker.sudoku.models

typealias InvalidInputException = java.lang.RuntimeException

class BoardInputValidator(val input: List<List<Int?>>) {
    companion object {
        const val MAX_ROWS = 9
        const val MAX_COLUMNS = 9
    }

    fun validate(): List<List<Int>> {
        val validatedInput = ArrayList<ArrayList<Int>>()
        input.forEachIndexed { rowIdx, row ->
            if (rowIdx >= MAX_ROWS) {
                throw InvalidInputException("board has more than 9 rows")
            }
            val validatedRow = ArrayList<Int>()
            row.forEachIndexed { colIdx, value ->
                if (colIdx >= MAX_COLUMNS) {
                    throw InvalidInputException("board has more than 9 columns")
                }
                val correctedValue = value ?: 0
                validatedRow.add(correctedValue)
            }
            validatedInput.add(validatedRow)
        }
        return validatedInput
    }
}
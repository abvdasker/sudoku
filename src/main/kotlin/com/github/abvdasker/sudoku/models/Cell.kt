package com.github.abvdasker.sudoku.models

class Cell(data: Int?) {
    var inferred: Int
    var wasSet: Boolean

    init {
        if (data != null && data != 0) {
            wasSet = true
            inferred = data
        } else {
            wasSet = false
            inferred = 0
        }
    }

    fun next(direction: Direction): Boolean {
        if (wasSet) {
            if (direction == Direction.FORWARD) {
                return true
            }
            return false
        }

        if (inferred + 1 > 9) {
            inferred = 0
            return false
        }

        inferred++
        return true
    }

    fun getInferredString(): String {
        return inferred.toString()
    }
}
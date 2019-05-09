package com.github.abvdasker.sudoku.models

class Cell(val initial: Int?) {
    var inferred: Int

    init {
        inferred = if (initial != null) {
            initial
        } else {
            0
        }
    }

    constructor(): this(null)

    fun next(direction: Direction): Boolean {
        if (initial != null) {
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

    fun reset() {
        if (initial == null) {
            return
        }
        inferred = 0
    }
}
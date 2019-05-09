package com.github.abvdasker.sudoku

import com.github.abvdasker.sudoku.models.Board

val TEST_BOARD = arrayOf(
        arrayOf(null, null, 6, 8, 4, 3, 5, null, null),
        arrayOf(7, 4, null, 1, 9, null, null, null, null),
        arrayOf(null, null, null, 7, null, null, null, null, null),
        arrayOf(null, null, 4, null, 5, 6, 1, 8, null),
        arrayOf(null, 3, null, null, null, null, null, 5, null),
        arrayOf(null, 9, 5, 3, 2, null, 4, null, null),
        arrayOf(null, null, null, null, null, 8, null, null, null),
        arrayOf(null, null, null, null, 1, 4, null, 3, 8),
        arrayOf(null, null, 8, 2, 3, 9, 7, null, null)
)

fun main(args: Array<String>) {
    val board = Board(TEST_BOARD)
    println("UNSOLVED:")
    println(board)
    board.solve()
    println("SOLVED:")
    println(board)
}
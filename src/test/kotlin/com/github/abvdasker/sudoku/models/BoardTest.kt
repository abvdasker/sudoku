package com.github.abvdasker.sudoku.modelstest

import com.github.abvdasker.sudoku.models.Board
import kotlin.test.Test
import kotlin.test.assertEquals

class BoardTest {
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

    val EXPECTED_OUTPUT = """
1 2 6 | 8 4 3 | 5 7 9
7 4 3 | 1 9 5 | 8 2 6
5 8 9 | 7 6 2 | 3 4 1
----------------------
2 7 4 | 9 5 6 | 1 8 3
6 3 1 | 4 8 7 | 9 5 2
8 9 5 | 3 2 1 | 4 6 7
----------------------
3 1 2 | 5 7 8 | 6 9 4
9 5 7 | 6 1 4 | 2 3 8
4 6 8 | 2 3 9 | 7 1 5

""".trimIndent()

    @Test fun testSolve() {
        val board = Board(TEST_BOARD)

        board.solve()

        val result = board.toString()
        assertEquals(EXPECTED_OUTPUT, result)
    }
}

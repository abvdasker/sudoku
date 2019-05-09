package com.github.abvdasker.sudoku.modelstest

import com.github.abvdasker.sudoku.models.Board
import kotlin.test.Test
import kotlin.test.assertEquals

class BoardTest {
    companion object {
        val TEST_BOARD = listOf(
                listOf(0, 0, 6, 8, 4, 3, 5, 0, 0),
                listOf(7, 4, 0, 1, 9, 0, 0, 0, 0),
                listOf(0, 0, 0, 7, 0, 0, 0, 0, 0),
                listOf(0, 0, 4, 0, 5, 6, 1, 8, 0),
                listOf(0, 3, 0, 0, 0, 0, 0, 5, 0),
                listOf(0, 9, 5, 3, 2, 0, 4, 0, 0),
                listOf(0, 0, 0, 0, 0, 8, 0, 0, 0),
                listOf(0, 0, 0, 0, 1, 4, 0, 3, 8),
                listOf(0, 0, 8, 2, 3, 9, 7, 0, 0)
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
    }

    @Test fun testSolve() {
        val board = Board(TEST_BOARD)

        board.solve()

        val result = board.toString()
        assertEquals(EXPECTED_OUTPUT, result)
    }
}

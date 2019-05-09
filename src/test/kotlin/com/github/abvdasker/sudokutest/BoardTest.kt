package com.github.abvdasker.sudokutest

import org.junit.Assert.assertArrayEquals
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

import com.github.abvdasker.sudoku.Board
import com.github.abvdasker.sudoku.errors.InvalidInputException
import com.github.abvdasker.sudoku.errors.UnsolvableException

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

        val INVALID_TEST_BOARD = listOf(
                listOf(6, 0, 6, 8, 4, 3, 5, 0, 0), // <- extra 6 here makes this impossible
                listOf(7, 4, 0, 1, 9, 0, 0, 0, 0),
                listOf(0, 0, 0, 7, 0, 0, 0, 0, 0),
                listOf(0, 0, 4, 0, 5, 6, 1, 8, 0),
                listOf(0, 3, 0, 0, 0, 0, 0, 5, 0),
                listOf(0, 9, 5, 3, 2, 0, 4, 0, 0),
                listOf(0, 0, 0, 0, 0, 8, 0, 0, 0),
                listOf(0, 0, 0, 0, 1, 4, 0, 3, 8),
                listOf(0, 0, 8, 2, 3, 9, 7, 0, 0)
        )

        val IMPOSSIBLE_TEST_BOARD = listOf(
                listOf(0, 0, 6, 8, 4, 3, 5, 0, 0),
                listOf(7, 4, 0, 1, 9, 0, 0, 0, 0),
                listOf(0, 0, 0, 7, 0, 0, 0, 0, 0),
                listOf(0, 0, 4, 0, 5, 6, 1, 8, 0),
                listOf(0, 3, 0, 0, 0, 0, 0, 5, 0),
                listOf(0, 9, 5, 3, 2, 0, 4, 0, 2),
                listOf(0, 2, 0, 0, 0, 8, 0, 2, 0),
                listOf(2, 0, 0, 0, 1, 4, 0, 3, 8),
                listOf(0, 0, 8, 2, 3, 9, 7, 0, 0)
        )

        val EXPECTED_CSV_LINES = arrayListOf<Array<String?>>(
                arrayOf("0", "0", "6", "8", "4", "3", "5", "0", "0"),
                arrayOf("7", "4", "0", "1", "9", "0", "0", "0", "0"),
                arrayOf("0", "0", "0", "7", "0", "0", "0", "0", "0"),
                arrayOf("0", "0", "4", "0", "5", "6", "1", "8", "0"),
                arrayOf("0", "3", "0", "0", "0", "0", "0", "5", "0"),
                arrayOf("0", "9", "5", "3", "2", "0", "4", "0", "0"),
                arrayOf("0", "0", "0", "0", "0", "8", "0", "0", "0"),
                arrayOf("0", "0", "0", "0", "1", "4", "0", "3", "8"),
                arrayOf("0", "0", "8", "2", "3", "9", "7", "0", "0")
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

        assertEquals(EXPECTED_OUTPUT, board.toString())
    }

    @Test fun testSolve_invalid() {
        val board = Board(INVALID_TEST_BOARD)

        assertFailsWith(InvalidInputException::class, "") {
            board.solve()
        }
    }

    @Test fun testSolve_impossible() {
        val board = Board(IMPOSSIBLE_TEST_BOARD)

        assertFailsWith(UnsolvableException::class, "") {
            board.solve()
        }
    }

    @Test fun testToCSVLines() {
        val board = Board(TEST_BOARD)

        val csvLines = board.toCSVLines()

        csvLines.forEachIndexed { rowIdx, row ->
            val expectedRow = EXPECTED_CSV_LINES[rowIdx]
            assertArrayEquals(expectedRow, row)
        }
    }

}

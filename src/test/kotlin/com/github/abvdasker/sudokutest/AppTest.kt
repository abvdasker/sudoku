package com.github.abvdasker.sudokutest

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

import com.github.abvdasker.sudoku.main

class AppTest {
    companion object {
        const val SAMPLE_FILE_PATH = "src/test/resources/sample_1_solved.csv"
        const val SAMPLE_SOLUTION_FILE_PATH = "src/test/resources/sample_1_solved.csv"
    }

    @Test
    fun testMain() {
        val outputFile = File.createTempFile("sudoku-test", ".csv")
        val outputFilepath = outputFile.absolutePath
        val args = arrayOf("-i", SAMPLE_FILE_PATH, "-o", outputFilepath)

        main(args)

        val outputFileBytes = outputFile.readBytes()
        val outputFileString = String(outputFileBytes)

        val expectedOutputBytes = File(SAMPLE_SOLUTION_FILE_PATH).readBytes()
        val expectedOutputString = String(expectedOutputBytes)

        assertEquals(expectedOutputString, outputFileString)
    }
}
package com.github.abvdasker.sudoku

import java.io.FileReader
import java.io.FileWriter
import java.io.File
import kotlin.system.exitProcess

import com.opencsv.CSVReader
import com.opencsv.CSVWriter

import com.github.abvdasker.sudoku.cli.Parser
import com.github.abvdasker.sudoku.models.Board
import com.github.abvdasker.sudoku.models.BoardInputValidator

fun main(args: Array<String>) {
    val parser = Parser()
    parser.main(args)

    val csvFile = File(parser.input)
    if (!csvFile.exists()) {
        println("Input CSV file ${parser.input} does not exist")
        exitProcess(1)
    }
    if (!csvFile.isFile) {
        println("Input CSV file ${parser.input} is not a file")
        exitProcess(1)
    }

    val outputCSVFile = File(parser.output)
    if (outputCSVFile.isDirectory()) {
        println("Output CSV file ${parser.output} is a directory")
        exitProcess(1)
    }

    val csvLines = readLinesFromCSVFile(csvFile)
    val boardInput = adaptCSVLinesToBoardInput(csvLines)
    val validatedBoardInput = BoardInputValidator(boardInput).validate()
    val board = Board(validatedBoardInput)

    board.solve()

    val solvedCSVLines = board.toCSVLines()
    writeLinesToCSVFile(solvedCSVLines, outputCSVFile)
}

fun readLinesFromCSVFile(csvFile: File): List<Array<String?>> {
    val fileReader = FileReader(csvFile)
    val csvReader = CSVReader(fileReader)
    val csvLines = csvReader.readAll()
    return csvLines
}

fun writeLinesToCSVFile(solvedCSVLines: List<Array<String?>>, outputCSVFile: File) {
    val outputCSVFileWriter = FileWriter(outputCSVFile)
    val csvWriter = CSVWriter(outputCSVFileWriter)
    csvWriter.writeAll(solvedCSVLines, false)
    csvWriter.flush()
}

fun adaptCSVLinesToBoardInput(csvLines: List<Array<String?>>): List<List<Int?>> {
    val boardInput = ArrayList<ArrayList<Int?>>()
    csvLines.forEachIndexed { rowIdx, row ->
        val parsedRow = ArrayList<Int?>()

        row.forEachIndexed { colIdx, value ->
            var correctedValue = value
            if (correctedValue == null) {
                correctedValue = "0"
            }

            try {
                val parsedValue = Integer.parseInt(correctedValue)
                parsedRow.add(parsedValue)
            } catch (err: NumberFormatException) {
                throw RuntimeException("value $value at $rowIdx, $colIdx is not a number", err)
            }
        }
        boardInput.add(parsedRow)
    }
    return boardInput
}
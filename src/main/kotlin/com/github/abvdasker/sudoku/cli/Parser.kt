package com.github.abvdasker.sudoku.cli

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required

class Parser: CliktCommand() {
    val input by option(
            "-i", "--input",
            help="the sudoku input csv"
    ).required()

    val output by option(
            "-o", "--output",
            help="the solved sudoku output file"
    ).required()

    override fun run() {
    }
}
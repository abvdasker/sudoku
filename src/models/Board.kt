package com.github.abvdasker.models

class Board(input: Array<Array<Int?>>) {
    private val backing: ArrayList<ArrayList<Cell>> = ArrayList()

    init {
        for (rowIdx in 0 until input.size) {
            val row = input.get(rowIdx)
            val backingRow = ArrayList<Cell>()
            for (colIdx in 0 until row.size) {
                val cellValue = row[colIdx]
                val cell = Cell(cellValue)
                backingRow.add(cell)
            }
            backing.add(backingRow)
        }
    }

    override fun toString(): String {
        val output = StringBuilder()
        for (rowIdx in 0 until backing.size) {
            val row = backing.get(rowIdx)
            val rowString = formatRow(row)
            val extraRowString = maybeAddRow(rowIdx, rowString)
            output.append(rowString)
            output.append(extraRowString)
        }
        return output.toString()
    }

    fun solve() {
        var direction = Direction.FORWARD
        var rowIdx = 0
        var colIdx = 0

        while (true) {
            val current = backing[rowIdx][colIdx]
            if (current.next(direction)) {
                direction = Direction.FORWARD
                val isValid = isValid(current, rowIdx, colIdx)

                if (isValid) {
                    val (tmpRowIdx, tmpColIdx, finished) = moveForward(rowIdx, colIdx)
                    if (finished) {
                        return
                    }
                    rowIdx = tmpRowIdx
                    colIdx = tmpColIdx
                }
            } else {
                direction = Direction.BACKWARD
                val (tmpRowIdx, tmpColIdx, impossible) = moveBackward(rowIdx, colIdx)
                if (impossible) {
                    throw RuntimeException("board is unsolvable")
                }
                rowIdx = tmpRowIdx
                colIdx = tmpColIdx
            }
        }
    }

    private fun moveForward(rowIdx: Int, colIdx: Int): Triple<Int, Int, Boolean> {
        if (colIdx < backing[rowIdx].size - 1) {
            return Triple(rowIdx, colIdx + 1, false) // next column
        }

        if (rowIdx < backing.size - 1) {
            return Triple(rowIdx + 1, 0, false) // next row
        }

        return Triple(-1, -1, true) // nowhere else to go. Done!
    }

    private fun moveBackward(rowIdx: Int, colIdx: Int): Triple<Int, Int, Boolean> {
        if (colIdx > 0) {
            return Triple(rowIdx, colIdx - 1, false) // previous column
        }

        if (rowIdx > 0) {
            return Triple(rowIdx - 1, backing[rowIdx - 1].size - 1, false) // previous row
        }

        return Triple(-1, -1, true) // nowhere else to go. Unsolvable
    }

    private fun isValid(current: Cell, rowIdx: Int, colIdx: Int): Boolean {
        return isValidRow(current, rowIdx, colIdx) &&
                isValidCol(current, rowIdx, colIdx) &&
                isValidBox(current, rowIdx, colIdx)
    }

    private fun isValidRow(current: Cell, rowIdx: Int, colIdx: Int): Boolean {
        val row = backing[rowIdx]
        row.forEachIndexed { i, cell ->
            if (i == colIdx) {
                return@forEachIndexed
            }

            if (cell.inferred == current.inferred) {
                return false
            }
        }

        return true
    }

    private fun isValidCol(current: Cell, rowIdx: Int, colIdx: Int): Boolean {
        for (i in 0 until backing.size) {
            if (i == rowIdx) {
                continue
            }

            val cell = backing[i][colIdx]
            if (cell.inferred == current.inferred) {
                return false
            }
        }

        return true
    }

    private fun isValidBox(current: Cell, rowIdx: Int, colIdx: Int): Boolean {
        val lowerRow = (rowIdx / 3) * 3
        val upperRow = lowerRow + 2

        val lowerCol = (colIdx / 3) * 3
        val upperCol = lowerCol + 2

        for (row in lowerRow..upperRow) {
            for (col in lowerCol..upperCol) {
                if (row == rowIdx && col == colIdx) {
                    continue
                }
                val cell = backing[row][col]
                if (cell.inferred == current.inferred) {
                    return false
                }
            }
        }

        return true
    }

    private fun formatRow(row: ArrayList<Cell>): StringBuilder {
        val rowString = StringBuilder()
        for (colIdx in 0 until row.size) {
            val cell = row.get(colIdx)
            rowString.append(cell.inferred)
            if (colIdx < row.size - 1) {
                rowString.append(' ')

                if ((colIdx + 1) % 3 == 0) {
                    rowString.append('|')
                    rowString.append(' ')
                }
            }
        }
        rowString.append('\n')
        return rowString
    }

    private fun maybeAddRow(rowIdx: Int, rowString: StringBuilder): StringBuilder {
        val extraRowString = StringBuilder()
        if ((rowIdx + 1) % 3 == 0 && rowIdx < backing.size - 1) {
            for (colIdx in 0 until rowString.length) {
                extraRowString.append('-')
            }
            extraRowString.append('\n')
        }
        return extraRowString
    }
}
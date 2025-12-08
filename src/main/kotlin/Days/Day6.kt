package org.gatlin.Days

import org.gatlin.Helpers

class Day6 : Day() {
    override fun solve() {
        super.solve()
        val input = Helpers.readFileFromResources("day6.txt")
        val rows = input.trimEnd().lines()
        val signs = rows.last().trim().split(Regex("\\s+"))

        val nums = rows.dropLast(1).map { row ->
            row.trim().split(Regex("\\s+"))
        }
        println("========= Part 1 =========")
        var total = 0L
        for (i in signs.indices) {
            val op = signs[i]
            val curNums: List<Long> = nums.map { row -> row[i].toLong() }
            val value = if (op == "+") {
                curNums.sum()
            } else {
                curNums.reduce { acc, v -> acc * v }
            }
            total += value
        }
        println("total: $total")

        println("========= Part 2 ==========")
        val height = rows.size
        val width = rows.maxOf { it.length }

        val grid = Array(height) { r ->
            val row = rows[r]
            CharArray(width) { c -> if (c < row.length) row[c] else ' ' }
        }

        val gridHeight = grid.size
        val gridWidth = grid[0].size
        val lastRow = height - 1

        fun isSeparatorCol(col: Int): Boolean {
            for (r in 0 until gridHeight) {
                if (grid[r][col] != ' ') return false
            }
            return true
        }

        val problemCols = mutableListOf<IntRange>()
        var c = 0
        while (c < gridWidth) {
            // skip separator columns (all spaces)
            while (c < gridWidth && isSeparatorCol(c)) c++
            if (c >= gridWidth) break
            val start = c
            while (c < gridWidth && !isSeparatorCol(c)) c++
            val end = c - 1
            problemCols.add(start..end)
        }
        total = 0L
        for (range in problemCols) {
            var op: Char? = null
            for (col in range) {
                val ch = grid[lastRow][col]
                if (ch == '+' || ch == '*') {
                    op = ch
                    break
                }
            }
            if (op == null) error("No operator found in problem columns $range")
            val numbers = mutableListOf<Long>()
            for (col in range) {
                val sb = StringBuilder()
                for (row in 0 until lastRow) {
                    val ch = grid[row][col]
                    if (ch != ' ') sb.append(ch)
                }
                if (sb.isNotEmpty()) {
                    numbers.add(sb.toString().toLong())
                }
            }
            if (numbers.isEmpty()) continue
            val value = if (op == '+') {
                numbers.sum()
            } else {
                var prod = 1L
                for (v in numbers) prod *= v
                prod
            }
            total += value
        }

        print("total: $total")
    }
}
package org.gatlin.Days

import org.gatlin.Helpers
import java.math.BigInteger

class Day3: Day() {
    override fun solve() {
        super.solve()
        val input = Helpers.readFileFromResources("day3.txt")
        val banks = input.split("\n")

        println("======= Part 1 =======")
        var total: Long = 0
        banks.forEach{ bank ->
            //1234588789
            var firstDigit = bank.reversed().toCharArray().drop(1).max()
            var secondDigit = bank.toCharArray().drop(bank.toCharArray().indexOf(firstDigit) + 1).max()
            var number = ("$firstDigit"+"$secondDigit").toInt()
            total += number
        }
        println(total)

        println("======= Part 2 =========")
        total = 0
        banks.forEach{ bank ->
            total += maxNumberOf12Digits(bank).toLong()
        }
        println(total)
    }
    private fun maxNumberOf12Digits(bank: String): String {
        val digits = bank.trim().toCharArray()
        val n = digits.size
        require(12 <= n)

        val result = StringBuilder()
        var start = 0
        var remaining = 12

        repeat(12) {
            val end = n - remaining
            var bestDigit = '0'
            var bestIndex = start
            for (i in start..end) {
                val d = digits[i]
                if (d > bestDigit) {
                    bestDigit = d
                    bestIndex = i
                }
            }
            result.append(bestDigit)
            start = bestIndex + 1
            remaining--
        }

        return result.toString()
    }

}
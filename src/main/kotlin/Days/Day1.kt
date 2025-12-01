package org.gatlin.Days

import org.gatlin.Helpers

class Day1 : Day() {
    override fun solve() {
        super.solve()
        println("========= Part 1 =========")
        val input = Helpers.readFileFromResources("day1.txt")
        var curIndex = 50
        var instruction: Pair<Char, Int>
        var password = 0
        for(line in input.split("\n")){
            instruction = Pair(line.first(), line.substring(1).toInt())
            curIndex = if(instruction.first == 'R') {
                normalizeNum(curIndex + instruction.second)
            }else{
                normalizeNum(curIndex- instruction.second)
            }
            if(curIndex == 0){
                password++
            }
        }
        println(password)

        println("========= Part 2 =========")
        var index = 50
        var password2 = 0

        for (line in input.split("\n")) {
            val instr = line.trim()
            val dir = instr.first()
            val dist = instr.substring(1).toInt()

            val (newIndex, newPassword) =
                if (dir == 'R') {
                    stepRight(index, dist, password2)
                } else {
                    stepLeft(index, dist, password2)
                }

            index = newIndex
            password2 = newPassword
        }

        println(password2)
    }

    fun normalizeNum(num: Int): Int{
        if(num > 99){
            return num%100
        }else if(num < 0){
            return num%-100
        }else{
            return num
        }
    }

    fun wrap(x: Int): Int =
        ((x % 100) + 100) % 100

    fun stepRight(start: Int, distance: Int, currentPassword: Int): Pair<Int, Int> {
        val hits = (start + distance) / 100
        val newIndex = wrap(start + distance)
        return Pair(newIndex, currentPassword + hits)
    }

    fun stepLeft(start: Int, distance: Int, currentPassword: Int): Pair<Int, Int> {
        val s = start
        val d = distance

        val firstK = if (s == 0) 100 else s
        val hits =
            if (d < firstK) {
                0
            } else {
                1 + (d - firstK) / 100
            }

        val newIndex = wrap(s - d)
        return Pair(newIndex, currentPassword + hits)
    }
}
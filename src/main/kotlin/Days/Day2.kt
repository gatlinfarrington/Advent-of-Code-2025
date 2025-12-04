package org.gatlin.Days

import org.gatlin.Helpers

class Day2: Day() {
    override fun solve() {
        super.solve()
        println("========= Part 1 =========")
        val input = Helpers.readFileFromResources("day2.txt")
        val ranges = input.split(",").filter { it.isNotBlank() }
        var total = 0L
        ranges.forEach{range ->
            val (start, end) = range.split("-")
            val s = start.toLong()
            val e = end.toLong()
            for(id in s..e){
                if(isInvalid(id)){
                    total += id
                }
            }
        }
        println(total)

        println("========= Part 2 =========")
        total = 0L
        ranges.forEach{range ->
            val (start, end) = range.split("-")
            val s = start.toLong()
            val e = end.toLong()
            for(id in s..e){
                if(isInvalidPart2(id)){
                    total += id
                }
            }
        }
        println(total)
    }

    fun isInvalid(id: Long): Boolean{
        val s = id.toString()
        if (s.length % 2 != 0) return false

        if(s.substring(0, s.length/2) != s.substring(s.length/2)){
            return false
        }
        return true
    }

    fun isInvalidPart2(id: Long): Boolean{
        val s = id.toString()
        var invalid = false
        for(l in 1..s.length/2){
            val parts = s.chunked(l)
            if(parts.all { it == parts[0] }){
                invalid = true
            }
        }
        return invalid
    }

}
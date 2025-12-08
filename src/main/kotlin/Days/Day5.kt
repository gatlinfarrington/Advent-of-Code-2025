package org.gatlin.Days

import org.gatlin.Helpers

class Day5: Day() {
    override fun solve() {
        super.solve()
        val input = Helpers.readFileFromResources("day5.txt")
        var (ranges, ids) =    input.split("\n\n")
        var rangesList = ranges.split("\n")
        var idsList = ids.split("\n")
        println("======== Part 1 ===========")
        var freshIds: MutableList<Range> = mutableListOf()
        rangesList.forEach{ span ->
            freshIds.add(Range(span.split("-").first().toLong(), span.split("-").last().toLong()))
        }

        var freshCount = 0
        idsList.forEach {
            for(r in freshIds){
                if(it.toLong() >= r.start && it.toLong() <= r.end){
                    freshCount += 1
                    break
                }
            }
        }
        println("Fresh Count: $freshCount")

        println("========== Part 2 =========")
        val sortedRanges: List<Range> = freshIds.sortedBy { it.start }
        val merged = mutableListOf<Range>()
        var current = sortedRanges[0]
        for(i in 1 until sortedRanges.size ){
            if(current.end >= sortedRanges[i].start){
                current = Range(current.start, maxOf(current.end, sortedRanges[i].end))
            }else{
                merged.add(current)
                current = sortedRanges[i]
            }
        }
        merged.add(current)

        val totalFreshIds: Long = merged.fold(0L) { acc, r ->
            acc + (r.end - r.start + 1)
        }

        println("Total fresh IDs: $totalFreshIds")

    }

    data class Range(val start: Long, val end: Long)
}
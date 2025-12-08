package org.gatlin.Days

import org.gatlin.Helpers

class Day4: Day() {
    override fun solve() {
        super.solve()
        val input = Helpers.readFileFromResources("day4.txt")
        val rows = input.split("\n").map { row -> row.toCharArray() }.toTypedArray()
        println("======== Part 1 =========")

        var accessibleRolls = 0
        for(i in 0..rows.size-1){
            for(j in 0..rows[i].size-1){
                if(rows[i][j] == '@'){
                    if(exploreAdjacent(rows, i, j) < 4){
                        accessibleRolls += 1
                    }
                }
            }
        }

        println("Accessible Rolls: $accessibleRolls")

        println("====== Part 2 ========")
        var newRolls = 0
        accessibleRolls = 0
        do{
            newRolls = 0
            for(i in 0..rows.size-1){
                for(j in 0..rows[i].size-1){
                    if(rows[i][j] == '@'){
                        if(exploreAdjacent(rows, i, j) < 4){
                            newRolls += 1
                            rows[i][j] = '.'
                        }
                    }
                }
            }
            accessibleRolls += newRolls
        }while(newRolls > 0)

        println("Removed Rolls: $accessibleRolls")
    }

    private fun exploreAdjacent(grid: Array<CharArray>, row: Int, col: Int): Int{
        //This could be super condensed, kind of like the verbose check for each option though
        var adjacentRolls = 0
        // look at mins and max to avoid indexOutOfBounds
        val doBackCol = (col-1)>=0
        val doForCol = (col+1)<grid[0].size
        val doBackRow = (row-1)>=0
        val doForRow = (row+1) < grid.size

        //check all adjacent
        //TL
        if(doBackCol && doBackRow){
            if(`is@`(grid[row-1][col-1])){
                adjacentRolls+=1
            }
        }
        //T
        if(doBackRow){
            if(`is@`(grid[row-1][col])){
                adjacentRolls+=1
            }
        }
        //TR
        if(doBackRow && doForCol){
            if(`is@`(grid[row-1][col+1])){
                adjacentRolls+=1
            }
        }
        //L
        if(doBackCol){
            if(`is@`(grid[row][col-1])){
                adjacentRolls+=1
            }
        }
        //R
        if(doForCol){
            if(`is@`(grid[row][col+1])){
                adjacentRolls+=1
            }
        }
        //DL
        if(doBackCol && doForRow){
            if(`is@`(grid[row+1][col-1])){
                adjacentRolls+=1
            }
        }
        //D
        if(doForRow){
            if(`is@`(grid[row+1][col])){
                adjacentRolls+=1
            }
        }
        //DR
        if(doForCol && doForRow){
            if(`is@`(grid[row+1][col+1])){
                adjacentRolls+=1
            }
        }

        return adjacentRolls
    }

    private fun `is@`(c: Char): Boolean{
        return c == '@'
    }
}
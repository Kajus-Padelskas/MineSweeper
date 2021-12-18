package academy.learnprogramming.minesweeperv2.layout

import academy.learnprogramming.minesweeperv2.dataStructure.Cell

open class Board protected constructor(){
    lateinit var board : List<List<Cell>>
    protected var width = 0
    protected var height = 0

    constructor(width: Int, height: Int) : this() {
        this.width = width
        this.height = height
        this.board = CellGenerator.generateCells(width, height)
    }

    fun getAdjacentCells(y : Int, x: Int) : List<Cell> {
        val adjacentCells: ArrayList<Cell> = ArrayList()
        if(y + 1 < height) adjacentCells.add(board[y+1][x])
        if(y - 1 >= 0) adjacentCells.add(board[y-1][x])
        if(x + 1 < width) adjacentCells.add(board[y][x+1])
        if(x - 1 >= 0) adjacentCells.add(board[y][x-1])
        if(y + 1 < height && x + 1 < width) adjacentCells.add(board[y+1][x+1])
        if(y + 1 < height && x - 1 >= 0) adjacentCells.add(board[y+1][x-1])
        if(y - 1 >= 0 && x + 1 < width) adjacentCells.add(board[y-1][x+1])
        if(y - 1 >= 0 && x - 1 >= 0) adjacentCells.add(board[y-1][x-1])
        return adjacentCells.toList()
    }
}
package academy.learnprogramming.minesweeperv2.refactor

open class Board(val width: Int, val height: Int){

    protected val board = CellGenerator.generateCells(width, height)

    fun getCell(y : Int, x: Int) = board[y][x]

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
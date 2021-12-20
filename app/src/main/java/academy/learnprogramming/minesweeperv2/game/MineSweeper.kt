package academy.learnprogramming.minesweeperv2.game

import academy.learnprogramming.minesweeperv2.layout.MineBoard
import academy.learnprogramming.minesweeperv2.dataStructure.Cell

class MineSweeper(val boardWidth: Int, val boardHeight: Int, mineAmount: Int) : GameRules {

    private val gameBoard : MineBoard
    private var targetForRevealedCells : Int = 0
    private var gameWon: Boolean
    private var gameLost: Boolean
    val board : List<List<Cell>>
        get() = gameBoard.board

    init {
        targetForRevealedCells = boardWidth * boardHeight - mineAmount
        gameBoard = MineBoard(boardWidth, boardHeight, mineAmount)
        gameLost = false
        gameWon = false
    }

    fun revealCell(y: Int, x: Int): Cell {
        val currentCell = board[y][x]
        if (!currentCell.isFlagged) currentCell.isRevealed = true
        if (currentCell.isMine) gameLost = true
        if (targetForRevealedCells <= countRevealedCells()) gameWon = true
        if (currentCell.value == 0) gameBoard.revealAdjacentCells(y, x)
        return currentCell
    }

    fun setFlag(y: Int, x: Int): Cell {
        val currentCell = board[y][x]
        currentCell.isFlagged = !board[y][x].isFlagged
        return board[y][x]
    }

    private fun countRevealedCells(): Int {
        var revealedCells = 0
        for(cellRow in board){
            for(cell in cellRow){
                if(cell.isRevealed) revealedCells++
            }
        }
        return revealedCells
    }

    override fun isGameLost() = gameLost

    override fun isGameWon() = gameWon
}
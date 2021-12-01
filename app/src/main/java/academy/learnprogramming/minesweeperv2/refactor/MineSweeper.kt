package academy.learnprogramming.minesweeperv2.refactor

class MineSweeper(val width: Int, val height: Int, mineAmount: Int) : GameRules {

    private val board = MineBoard(width, height, mineAmount)
    private val targetForRevealedCells = width * height - mineAmount
    private var gameWon: Boolean = false
    private var gameLost: Boolean = false

    fun revealCell(y: Int, x: Int){

        val currentCell = board.getMineBoard()[y][x]

        if (!currentCell.isFlagged)
        currentCell.isRevealed = true
        if (currentCell.isMine) gameLost = true
        if (targetForRevealedCells <= countRevealedCells()) gameWon = true
        if (currentCell.value == 0)
        board.updateBoard(y, x)
    }

    fun setFlag(y: Int, x: Int){
        board.getMineBoard()[y][x].isFlagged = !board.getMineBoard()[y][x].isFlagged
    }

    fun getGameBoard() = board.getMineBoard()

    override fun isGameWon() = gameWon

    private fun countRevealedCells(): Int {

        var revealedCells = 0

        for(cellRow in board.getMineBoard()){
            for(cell in cellRow){
                if(cell.isRevealed) revealedCells++
            }
        }

        println("Revealed cell: $revealedCells , Target: $targetForRevealedCells")
        return revealedCells
    }

    override fun isGameLost() = gameLost
}
package academy.learnprogramming.minesweeperv2.grid

import android.util.Log

class MineSweeperGameHandler(private val mineSweeperGridBuilder: MineSweeperGridBuilder) :
    GameRules {

    val cells = mineSweeperGridBuilder.build()
    val width = mineSweeperGridBuilder.width
    val height = mineSweeperGridBuilder.height
    private val mineAmount = mineSweeperGridBuilder.mineAmount
    private var revealedCells = 0
    private val neededCellsToWin = width * height - mineAmount
    private var gameLost = false

    fun handleClick(y: Int, x: Int) {

        val currentCell = cells[y][x]
        if (isAvailableToClick(currentCell)) {
            currentCell.isRevealed = true

            if (currentCell.value == -1) {
                gameLost = true
                revealMines()
                return
            }

            if (currentCell.value == 0) {
                revealAdjacentCells(y, x)
            }

            revealedCells++
        }
    }

    fun isAvailableToClick(cell: Cell): Boolean {
        return !cell.isFlagged && !cell.isRevealed
    }

    fun setFlagged(y: Int, x: Int) {
        cells[y][x].isFlagged = !cells[y][x].isFlagged
    }

    fun revealMines() {
        val minePositions = mineSweeperGridBuilder.xAndY
        for (pos in minePositions) {
            cells[pos.first][pos.second].isRevealed = true
        }
    }

    fun revealAdjacentCells(y: Int, x: Int) {

        val adjacentCells = mineSweeperGridBuilder.adjacentCells(y, x).toHashSet()
        val toReveal = HashSet<Cell>()
        var size = adjacentCells.size

        while (size != toReveal.size) {
            toReveal.addAll(adjacentCells)
            size = toReveal.size
            for (cell in toReveal) {
                if (!cell.isRevealed)
                    revealedCells++
                cell.isRevealed = true


                if (cell.value == 0) adjacentCells.addAll(
                    mineSweeperGridBuilder.adjacentCells(
                        cell.pos.first,
                        cell.pos.second
                    )
                )
            }
            toReveal.addAll(adjacentCells)
        }
    }

    override fun isGameWon(): Boolean {
        Log.i("Revealed Cell Count: ", "$revealedCells $neededCellsToWin")
        return revealedCells == neededCellsToWin
    }

    override fun isGameLost(): Boolean {
        return gameLost
    }


}
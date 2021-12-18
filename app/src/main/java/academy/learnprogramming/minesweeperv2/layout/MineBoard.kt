package academy.learnprogramming.minesweeperv2.layout

import academy.learnprogramming.minesweeperv2.dataStructure.Cell
import academy.learnprogramming.minesweeperv2.logs.BoardLogger
import academy.learnprogramming.minesweeperv2.logs.Logger

const val BOMB = -1

class MineBoard(width: Int, height: Int, private var mineAmount: Int) :
    Board(width, height) {
    var minePos: List<Pair<Int, Int>> = emptyList()
    private val logger : Logger = BoardLogger(this)

    init {
        generateMinePos()
        for (pos in minePos) {
            board[pos.first][pos.second].isMine = true
            board[pos.first][pos.second].value = BOMB
            incrementNeighbourCells(pos.first, pos.second)
        }
        logger.log()
    }

    private fun generateMinePos() {
        val cords: HashSet<Pair<Int, Int>> = HashSet()

        while (cords.size < mineAmount) {
            cords.add(Pair((0 until height).random(), (0 until width).random()))
        }
        minePos = cords.toList()
    }

    fun revealAdjacentCells(y: Int, x: Int) {
        val adjacentCells = getAdjacentCells(y,x).toHashSet()
        val toReveal = HashSet<Cell>()
        var size = adjacentCells.size

        while (size != toReveal.size) {
            toReveal.addAll(adjacentCells)
            size = toReveal.size
            for (cell in toReveal) {
                cell.isRevealed = true
                if (cell.value == 0) adjacentCells.addAll(getAdjacentCells(cell.pos.first, cell.pos.second))
            }
            toReveal.addAll(adjacentCells)
        }
    }

    private fun incrementNeighbourCells(y : Int, x: Int) {
        val cells = getAdjacentCells(y,x)

        for(cell in cells) {
            if (!cell.isMine)
                cell.value++
        }
    }
}
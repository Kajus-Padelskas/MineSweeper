package academy.learnprogramming.minesweeperv2.refactor

class MineBoard(width: Int, height: Int, private val mineAmount: Int) : Board(width, height) {

    init {

        val minePos = generateMinePos()

        for (pos in minePos) {
            board[pos.first][pos.second].isMine = true
            board[pos.first][pos.second].value = -1
            incrementNeighbourCells(pos.first, pos.second)
        }
    }

    private fun generateMinePos(): List<Pair<Int, Int>> {

        val cords: HashSet<Pair<Int, Int>> = HashSet()

        while (cords.size < mineAmount) {
            cords.add(Pair((0 until height).random(), (0 until width).random()))
        }

        return cords.toList()
    }

    private fun incrementNeighbourCells(y : Int, x: Int) {

        val cells = getAdjacentCells(y,x)

        for(cell in cells) {
            if (!cell.isMine)
                cell.value++
        }

    }

    fun getMineBoard(): List<List<Cell>> {
        return board
    }

    fun updateBoard(y: Int, x: Int) {

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

}
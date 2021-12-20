package academy.learnprogramming.minesweeperv2

import academy.learnprogramming.minesweeperv2.layout.Board
import academy.learnprogramming.minesweeperv2.layout.MineBoard
import org.junit.Test

import org.junit.Assert.*

class BoardTest {

    private val board = Board(WIDTH, HEIGHT)

    @Test
    fun all_mines_in_board() {
        val mineAmount = 21

        val minePos = MineBoard(10, 10, mineAmount).minePos

        assertEquals(mineAmount, minePos.size)
    }

    @Test
    fun are_adjacent_cells() {
        val adjacentCells = listOf(
            board.getCell(0,0),
            board.getCell(0,1),
            board.getCell(0,2),
            board.getCell(1,0),
            board.getCell(1,2),
            board.getCell(2,0),
            board.getCell(2,1),
            board.getCell(2,2)
        )

        val cells = board.getAdjacentCells(1,1)

        assertEquals(true, cells.containsAll(adjacentCells))
    }

}
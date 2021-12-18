package academy.learnprogramming.minesweeperv2

import academy.learnprogramming.minesweeperv2.layout.MineBoard
import org.junit.Test

import org.junit.Assert.*

class BoardTest {

    @Test
    fun all_mines_in_board() {
        val mineAmount = 21

        val mineBoard = MineBoard(WIDTH, HEIGHT, mineAmount)

        assertEquals(mineAmount, mineBoard.minePos.size)
    }

}
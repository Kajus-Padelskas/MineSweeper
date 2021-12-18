package academy.learnprogramming.minesweeperv2

import academy.learnprogramming.minesweeperv2.command.FlagCommand
import academy.learnprogramming.minesweeperv2.command.RevealCommand
import academy.learnprogramming.minesweeperv2.game.MineSweeper
import org.junit.Test

import org.junit.Assert.*

const val WIDTH = 10
const val HEIGHT = 10
const val MINE_AMOUNT = 10
val mineSweeper = MineSweeper(WIDTH, HEIGHT, MINE_AMOUNT)

class ExampleUnitTest {

    @Test
    fun is_revealing_cell() {
        val targetY = 5
        val targetX = 2

        val cell = RevealCommand(mineSweeper, Pair(targetY,targetX)).execute()

        assertEquals(true, cell.isRevealed)
    }

    @Test
    fun is_flagging_cell() {
        val targetY = 5
        val targetX = 2

        val cell = FlagCommand(mineSweeper, Pair(targetY,targetX)).execute()

        assertEquals(true, cell.isFlagged)
    }

}
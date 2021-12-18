package academy.learnprogramming.minesweeperv2.command

import academy.learnprogramming.minesweeperv2.dataStructure.Cell
import academy.learnprogramming.minesweeperv2.game.MineSweeper

class FlagCommand(private val mineSweeper: MineSweeper, private val cords: Pair<Int,Int>) : Command {
    override fun execute() : Cell {
        return mineSweeper.setFlag(cords.first, cords.second)
    }

}
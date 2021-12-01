package academy.learnprogramming.minesweeperv2.command

import academy.learnprogramming.minesweeperv2.refactor.MineSweeper

class FlagCommand(private val mineSweeper: MineSweeper, private val cords: Pair<Int,Int>) : Command {

    override fun execute() {
        mineSweeper.setFlag(cords.first, cords.second)
    }

}
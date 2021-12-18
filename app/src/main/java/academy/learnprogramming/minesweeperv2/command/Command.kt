package academy.learnprogramming.minesweeperv2.command

import academy.learnprogramming.minesweeperv2.dataStructure.Cell

interface Command {
    fun execute() : Cell
}
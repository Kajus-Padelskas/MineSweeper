package academy.learnprogramming.minesweeperv2.logs

import academy.learnprogramming.minesweeperv2.layout.Board

class BoardLogger(private val board: Board) : Logger {

    override fun log() {
        for((indexRow, cellRow) in board.board.withIndex()){
            print("Cell Row $indexRow: ")
            for(cell in cellRow){
                print("${cell.value} ")
            }
            println()
        }
        println()
    }

}
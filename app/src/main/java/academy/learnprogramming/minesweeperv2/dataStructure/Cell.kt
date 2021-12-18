package academy.learnprogramming.minesweeperv2.dataStructure


open class Cell(val pos: Pair<Int, Int>) {

    var value = 0
    var isFlagged = false
    var isMine = false
    var isRevealed = false

    override fun toString(): String {
        return "$value"
    }

}
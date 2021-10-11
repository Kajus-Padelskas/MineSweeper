package academy.learnprogramming.minesweeperv2.grid

interface GameRules {
    fun isGameWon() : Boolean
    fun isGameLost() : Boolean
}
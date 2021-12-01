package academy.learnprogramming.minesweeperv2.refactor

interface GameRules {
    fun isGameWon() : Boolean
    fun isGameLost() : Boolean
}
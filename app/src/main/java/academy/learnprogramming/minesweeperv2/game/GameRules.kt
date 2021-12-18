package academy.learnprogramming.minesweeperv2.game

interface GameRules {
    fun isGameWon() : Boolean
    fun isGameLost() : Boolean
}
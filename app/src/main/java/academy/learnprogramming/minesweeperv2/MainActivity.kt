package academy.learnprogramming.minesweeperv2

import academy.learnprogramming.minesweeperv2.game.MineSweeper
import academy.learnprogramming.minesweeperv2.game.MineSweeperGameRender
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var restartButton: Button
    private lateinit var tvGameStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch()
    }

    private fun launch(){
        restartButton = findViewById(R.id.buttonReset)
        restartButton.setOnClickListener {
            launch()
            restartButton.text = getString(R.string.restart)
        }
        val game = MineSweeperGameRender
        game.render(this, MineSweeper(boardWidth = 10, boardHeight = 10, mineAmount = 10))
        tvGameStatus = findViewById(R.id.tvGameStatus)
        tvGameStatus.text = getString(R.string.game_in_progress)
    }
}
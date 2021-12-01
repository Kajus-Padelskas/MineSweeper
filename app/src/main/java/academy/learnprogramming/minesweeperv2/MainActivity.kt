package academy.learnprogramming.minesweeperv2

import academy.learnprogramming.minesweeperv2.refactor.MineSweeper
import academy.learnprogramming.minesweeperv2.refactor.MineSweeperRecyclerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var restartButton: Button
    private lateinit var tvGameStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        restartButton = findViewById(R.id.buttonReset)
        tvGameStatus = findViewById(R.id.tvGameStatus)

        restartButton.setOnClickListener {
            launch(10,10,5)
            restartButton.text = "Restart"
        }
    }

    private fun launch(width: Int, height: Int, mineAmount: Int){
        tvGameStatus.text = "Game in progress"
        val game = MineSweeper(width, height, mineAmount)
        val rv = findViewById<RecyclerView>(R.id.rvMineGrid)
        rv.adapter = MineSweeperRecyclerAdapter(this, game)
        rv.layoutManager = GridLayoutManager(rv.context,width)
    }

}
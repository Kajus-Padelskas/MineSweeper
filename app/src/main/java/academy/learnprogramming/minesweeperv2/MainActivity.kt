package academy.learnprogramming.minesweeperv2

import academy.learnprogramming.minesweeperv2.grid.GridAdapter
import academy.learnprogramming.minesweeperv2.grid.MineSweeperGameHandler
import academy.learnprogramming.minesweeperv2.grid.MineSweeperGridBuilder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var restartButton: Button
    private lateinit var tvGameStatus: TextView
    private val seekBars = ArrayList<Pair<SeekBar, TextView>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        restartButton = findViewById(R.id.buttonReset)
        tvGameStatus = findViewById(R.id.tvGameStatus)

        seekBars.add(Pair(findViewById(R.id.sbWidth), findViewById(R.id.tvWidthNumber)))
        seekBars.add(Pair(findViewById(R.id.sbHeight), findViewById(R.id.tvHeightNumber)))
        seekBars.add(Pair(findViewById(R.id.sbMines), findViewById(R.id.tvMineNumber)))

        for (seekBar in seekBars){
            seekBar.first.setOnSeekBarChangeListener(seekBarListener(seekBar.second))
            seekBar.second.text = seekBar.first.min.toString()
        }

        restartButton.setOnClickListener {
            launch(seekBars[0].first.progress,seekBars[1].first.progress,seekBars[2].first.progress)
            restartButton.text = "Restart"
        }
    }

    fun seekBarListener(view: TextView): SeekBar.OnSeekBarChangeListener {
        return object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                view.text = seek.progress.toString()
            }

            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped

            }
        }
    }

    fun launch(width: Int, height: Int, mineAmount: Int){
        tvGameStatus.text = "Game in progress"
        val game = MineSweeperGameHandler(MineSweeperGridBuilder(width,height,mineAmount))
        val rv = findViewById<RecyclerView>(R.id.rvMineGrid)
        rv.adapter = GridAdapter(this, game)
        rv.layoutManager = GridLayoutManager(this, width)
    }

}
package academy.learnprogramming.minesweeperv2.game

import academy.learnprogramming.minesweeperv2.R
import academy.learnprogramming.minesweeperv2.adapter.MineSweeperRecyclerAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

object MineSweeperGameRender {
    private lateinit var mineSweeperRecyclerView: RecyclerView

    fun render(activity: AppCompatActivity, game: MineSweeper) {
        mineSweeperRecyclerView = activity.findViewById(R.id.rvMineGrid)
        mineSweeperRecyclerView.adapter = MineSweeperRecyclerAdapter(activity, game)
        mineSweeperRecyclerView.layoutManager = GridLayoutManager(activity, game.boardWidth)
    }
}
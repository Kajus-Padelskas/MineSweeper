package academy.learnprogramming.minesweeperv2.adapter

import academy.learnprogramming.minesweeperv2.R
import academy.learnprogramming.minesweeperv2.command.FlagCommand
import academy.learnprogramming.minesweeperv2.command.RevealCommand
import academy.learnprogramming.minesweeperv2.game.MineSweeper
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

const val FLAG = "F"

class MineSweeperRecyclerAdapter(
    private val activity: AppCompatActivity,
    private val game: MineSweeper
) : RecyclerView.Adapter<MineSweeperRecyclerAdapter.CellHolder>() {
    lateinit var tvCell: TextView
    lateinit var tvGameStatus: TextView
    lateinit var bFlag: Button

    inner class CellHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            tvGameStatus = activity.findViewById(R.id.tvGameStatus)
            bFlag = activity.findViewById(R.id.buttonFlag)
            tvCell = view.findViewById(R.id.cellValue)
            bFlag.setOnClickListener {
                bFlag.isSelected = !bFlag.isSelected
            }
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            if (!game.isGameWon() && !game.isGameLost()) {
                if (bFlag.isSelected) {
                    FlagCommand(
                        game,
                        Pair(adapterPosition / game.boardWidth, adapterPosition % game.boardWidth)
                    ).execute()
                } else {
                    RevealCommand(
                        game,
                        Pair(adapterPosition / game.boardWidth, adapterPosition % game.boardWidth)
                    ).execute()
                    if (game.isGameWon()) tvGameStatus.text =
                        activity.getString(R.string.GameWon)
                    else if (game.isGameLost()) tvGameStatus.text =
                        activity.getString(R.string.GameLost)
                }
            }
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_cell, parent, false)
        return CellHolder(inflater)
    }

    override fun onBindViewHolder(holder: CellHolder, position: Int) {
        bindGameCells(position)
        holder.setIsRecyclable(false)
    }

    private fun bindGameCells(position: Int) {
        val currentCell =
            game.board[position / game.boardWidth][position % game.boardWidth]

        if (currentCell.isRevealed) {
            tvCell.text = currentCell.value.toString()
            when (currentCell.value) {
                0 -> tvCell.apply {
                    text = ""
                    setBackgroundColor(Color.LTGRAY)
                }
                1 -> tvCell.setTextColor(Color.BLUE)
                2 -> tvCell.setTextColor(Color.GREEN)
                3 -> tvCell.setTextColor(Color.RED)
            }
        } else if (currentCell.isFlagged) {
            tvCell.text = FLAG
            tvCell.setTextColor(Color.YELLOW)
        }
    }

    override fun getItemCount(): Int {
        return game.boardWidth * game.boardHeight
    }
}
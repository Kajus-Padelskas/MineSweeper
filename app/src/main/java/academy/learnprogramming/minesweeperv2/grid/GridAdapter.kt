package academy.learnprogramming.minesweeperv2.grid

import academy.learnprogramming.minesweeperv2.MainActivity
import academy.learnprogramming.minesweeperv2.R
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GridAdapter(
    private val activity: MainActivity,
    private val gameController: MineSweeperGameHandler
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var tvCell: TextView
    lateinit var tvGameStatus: TextView
    lateinit var flagButton: Button
    private val height = gameController.height
    private val width = gameController.width
    private var grid = gameController.cells

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            tvGameStatus = activity.findViewById(R.id.tvGameStatus)
            tvCell = itemView.findViewById(R.id.cellValue)
            flagButton = activity.findViewById(R.id.buttonFlag)
            flagButton.setOnClickListener {
                flagButton.isSelected = !flagButton.isSelected
            }
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {


            if (!gameController.isGameLost() && !gameController.isGameWon()) {
                if (!flagButton.isSelected) {
                    gameController.handleClick(adapterPosition / width, adapterPosition % width)
                    notifyDataSetChanged()
                    if (gameController.isGameWon()) tvGameStatus.text =
                        activity.getString(R.string.GameWon)
                    else if (gameController.isGameLost()) tvGameStatus.text =
                        activity.getString(R.string.GameLost)
                } else {
                    gameController.setFlagged(adapterPosition / width, adapterPosition % width)
                    notifyDataSetChanged()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_cell, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        bindData(position)
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int {
        return height * width
    }

    fun bindData(position: Int) {

        val cellCoord = Pair(position / width, position % width)
        val currentCell = grid[cellCoord.first][cellCoord.second]

        if (currentCell.isRevealed) {
            tvCell.text = currentCell.value.toString()
            if(currentCell.value == 0) {
                tvCell.text = ""
                tvCell.setBackgroundColor(Color.LTGRAY)
            }
            else if(currentCell.value == 1) tvCell.setTextColor(Color.BLUE)
            else if(currentCell.value == 2) tvCell.setTextColor(Color.GREEN)
            else if(currentCell.value == 3) tvCell.setTextColor(Color.RED)
        } else if (currentCell.isFlagged) {
            tvCell.text = "F"
            tvCell.setTextColor(Color.YELLOW)
        }
    }


}
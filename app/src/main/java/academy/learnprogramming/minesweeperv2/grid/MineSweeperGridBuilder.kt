package academy.learnprogramming.minesweeperv2.grid

import android.util.Log

class MineSweeperGridBuilder(val width: Int,val height: Int, val mineAmount: Int) {

    private val twoDimCellList = CellGenerator.generateCells(width,height)
    val xAndY = MineGenerator(mineAmount, width, height).generateMinePos()

    fun build() : List<List<Cell>> {

        for(cords in xAndY){

            twoDimCellList[cords.first][cords.second].apply {
                value = -1
                isMine = true
            }
            incrementNeighbourCells(cords.first, cords.second)

        }

        Log.i("Positions:", xAndY.toString())
        Log.i("Grid Size:", String.format("%s", twoDimCellList.size))
        Log.i("Mine Size:", String.format("%s", xAndY.size))
        for(cell in twoDimCellList){
            Log.i("Cell: ", cell.toString())
        }

        return twoDimCellList
    }

    fun adjacentCells(y: Int, x: Int): List<Cell>{

        val adjacentCells: ArrayList<Cell> = ArrayList()
        if(y + 1 < height) adjacentCells.add(twoDimCellList[y+1][x])
        if(y - 1 >= 0) adjacentCells.add(twoDimCellList[y-1][x])
        if(x + 1 < width) adjacentCells.add(twoDimCellList[y][x+1])
        if(x - 1 >= 0) adjacentCells.add(twoDimCellList[y][x-1])
        if(y + 1 < height && x + 1 < width) adjacentCells.add(twoDimCellList[y+1][x+1])
        if(y + 1 < height && x - 1 >= 0) adjacentCells.add(twoDimCellList[y+1][x-1])
        if(y - 1 >= 0 && x + 1 < width) adjacentCells.add(twoDimCellList[y-1][x+1])
        if(y - 1 >= 0 && x - 1 >= 0) adjacentCells.add(twoDimCellList[y-1][x-1])

        return adjacentCells.toList()
    }


    private fun incrementNeighbourCells(y : Int, x: Int) {

        val cells = adjacentCells(y,x)

        for(cell in cells) {
            if (!cell.isMine)
            cell.value++
        }

    }
}
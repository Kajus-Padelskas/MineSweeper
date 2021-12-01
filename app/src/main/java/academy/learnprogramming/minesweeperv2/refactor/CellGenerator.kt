package academy.learnprogramming.minesweeperv2.refactor

object CellGenerator {

    fun generateCells(width: Int, height: Int): List<List<Cell>> {

        val cellList = ArrayList<ArrayList<Cell>>()
        for (i in 0 until height) {
            cellList.add(ArrayList())
            for (j in 0 until width) {
                cellList[i].add(Cell(Pair(i,j)))
            }
        }

        return cellList
    }
}
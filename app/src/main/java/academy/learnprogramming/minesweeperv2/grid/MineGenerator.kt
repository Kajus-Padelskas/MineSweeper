package academy.learnprogramming.minesweeperv2.grid

class MineGenerator(private val mineAmount: Int, private val width: Int, private val height: Int) {

    fun generateMinePos(): List<Pair<Int,Int>> {

        val coords: HashSet<Pair<Int,Int>> = HashSet()

        while (coords.size < mineAmount){
            coords.add(Pair((0 until height).random(), (0 until width).random()))
        }
        return coords.toList()
    }


}
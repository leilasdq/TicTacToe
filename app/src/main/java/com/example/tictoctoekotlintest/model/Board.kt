package com.example.tictoctoekotlintest.model

class Board {
    companion object{
        const val PLAYER = "O"
        const val COMPUTER = "X"
    }

    val board = Array(3) { arrayOfNulls<String>(3) }

    fun moveOfPlayer(cell: Cell, player: String){
        board[cell.i][cell.j] = player
    }

    //This property is giving us a list of all the empty cells
    val availableCells: List<Cell>
        get() {
            val cells = mutableListOf<Cell>()
            for (i in board.indices) {
                for (j in board.indices) {
                    if (board[i][j].isNullOrEmpty()) {
                        cells.add(Cell(i, j))
                    }
                }
            }
            return cells
        }
}
package com.example.tictoctoekotlintest.controller


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.tictoctoekotlintest.R
import com.example.tictoctoekotlintest.model.Board
import com.example.tictoctoekotlintest.model.Cell
import kotlinx.android.synthetic.main.fragment_board.view.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 */
class BoardFragment : Fragment() {

    private val boardCells = Array(3) { arrayOfNulls<ImageView>(3) }
    private var board = Board()
    private var thisView: View? = null

    companion object { //Initialize statics
        fun newInstance(): BoardFragment {
            val args = Bundle()
            val fragment = BoardFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_board, container, false)

        thisView = view

        loadBoard(view)
        view.restart_btn?.setOnClickListener(View.OnClickListener {
            board = Board()
            thisView!!.winner_text.text = ""
            mapBoards()
        })

        return view
    }

    private fun mapBoards() {
        for (i in board.board.indices) {
            for (j in board.board.indices) {
                when (board.board[i][j]) {
                    /* 3 possibility:
                    1. player choose a cell
                    2. computer choose a cell
                    2. cell is empty */
                    Board.PLAYER -> { //put a circle in its ui cell
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled = false
                    }
                    Board.COMPUTER -> { //put a X in its ui cell
                        boardCells[i][j]?.setImageResource(R.drawable.cross)
                        boardCells[i][j]?.isEnabled = false
                    }
                    else -> {
                        boardCells[i][j]?.setImageDrawable(null)
                        boardCells[i][j]?.isEnabled = true
                    }
                }
            }
        }
    }

    private fun loadBoard(view: View) {
        for (i in boardCells.indices) {
            for (j in boardCells.indices) {
                boardCells[i][j] = ImageView(context)
                boardCells[i][j]?.layoutParams = GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 200
                    height = 200
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                boardCells[i][j]?.setBackgroundColor(
                    ContextCompat.getColor(
                        context!!.applicationContext,
                        R.color.colorPrimary
                    )
                )
                boardCells[i][j]?.setOnClickListener(OnCellClick(i, j))
                view.board.addView(boardCells[i][j])
            }
        }
    }

    inner class OnCellClick(var i: Int, var j: Int) : View.OnClickListener {
        override fun onClick(v: View?) {
            if (!board.isGameOver) {
                val cell = Cell(i, j)
                board.moveOfPlayer(
                    cell,
                    Board.PLAYER
                ) // it is always player bcz computer will play automatically

                if (board.availableCells.isNotEmpty()) {
                    val available =
                        board.availableCells[Random.nextInt(0, board.availableCells.size)]
                    board.moveOfPlayer(available, Board.COMPUTER)
                }
                mapBoards()
            }
            when {
                board.hasComputerWon() -> {
                    thisView?.winner_text?.text = getString(R.string.computer_winning_txt)
                }
                board.hasPlayerWon() -> {
                    thisView?.winner_text?.text = getString(R.string.player_winning_txt)
                }
                board.isGameOver -> {
                    thisView?.winner_text?.text = getString(R.string.tie_game_txt)
                }
            }
        }

    }

}

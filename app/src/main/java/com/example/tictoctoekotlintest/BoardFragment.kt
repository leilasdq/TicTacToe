package com.example.tictoctoekotlintest


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.example.tictoctoekotlintest.R
import kotlinx.android.synthetic.main.fragment_board.view.*

/**
 * A simple [Fragment] subclass.
 */
class BoardFragment : Fragment() {

    private val boardCells = Array(3){ arrayOfNulls <ImageView> (3)}

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

        loadBoard(view)

        return view
    }

    private fun loadBoard(view: View) {
        for (i in boardCells.indices){
            for (j in boardCells.indices){
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
                boardCells[i][j]?.setBackgroundColor(ContextCompat.getColor(context!!.applicationContext,
                    R.color.colorPrimary
                ))
                view.board.addView(boardCells[i][j])
            }
        }
    }

}

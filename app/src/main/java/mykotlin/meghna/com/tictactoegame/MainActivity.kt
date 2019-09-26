package mykotlin.meghna.com.tictactoegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , OnClickListener {
    var PLAYER = true
    var TURN_COUNT = 0
    var boardStatus=Array(3){IntArray(3)}

    lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board= arrayOf(
            arrayOf(button1,button2,button3),
            arrayOf(button4,button5,button6),
            arrayOf(button8,button9,button10)
        )
        for(i in board)
        {
            for(button in i)
            {
                button.setOnClickListener(this)
            }
        }
        initailizeBoardStatus()
        button11.setOnClickListener{
            PLAYER=true
            TURN_COUNT=0
            initailizeBoardStatus()

        }


    }

    private fun initailizeBoardStatus() {
        for(i in 0..2)
        {
            for(j in 0..2)
            {
                boardStatus[i][j] = -1

            }
        }
        for(i in board)
        {
            for(btn in i)
            {
                btn.isEnabled=true
                btn.text=""
            }
        }
    }

    override fun onClick(view: View) {
        when(view.id)
        {
            R.id.button1->{
            updateValue(row=0,col=0,player=PLAYER)
            }
            R.id.button2->{ 
                updateValue(row=0,col=1,player=PLAYER)
            }
            R.id.button3->{
                updateValue(row=0,col=2,player=PLAYER)

            }
            R.id.button4->{
                updateValue(row=1,col=0,player=PLAYER)
            }
            R.id.button5->{
                updateValue(row=1,col=1,player=PLAYER)
            }
            R.id.button6->{
                updateValue(row=1,col=2,player=PLAYER)
            }
            R.id.button8->{
                updateValue(row=2,col=0,player=PLAYER)
            }
            R.id.button9->{
                updateValue(row=2,col=1,player=PLAYER)
            }
            R.id.button10->{
                updateValue(row=2,col=2,player=PLAYER)
            }
        }
        TURN_COUNT++
        PLAYER= !PLAYER
        if(PLAYER)
        {
            updateDisplay("PLAYER X TURN")
        }
        else
        {
            updateDisplay("PLAYER 0 TURN")
        }
        if(TURN_COUNT==9)
        {
            updateDisplay("GAME DRAW")
        }
        checkWinner()
    }

    private fun checkWinner() {
        for (i in 0..2) {
            if (boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0] == boardStatus[i][2]) {
                if (boardStatus[i][0] == 1) {
                    updateDisplay("PLAYER X IS WINNER")
                    break
                } else if (boardStatus[i][0] == 0) {
                    updateDisplay("PLAYER 0 IS WINNER")
                    break
                }
            }
        }
        for(i in 0..2){
        if (boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i] == boardStatus[2][i]) {
            if (boardStatus[0][i] == 1) {
                updateDisplay("PLAYER X IS WINNER")
                break
            } else if (boardStatus[0][i] == 0) {
                updateDisplay("PLAYER 0 IS WINNER")
                break
            }
        }
    }

            if (boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]) {
                if (boardStatus[0][0] == 1) {
                    updateDisplay("PLAYER X IS WINNER")

                } else if (boardStatus[0][0] == 0) {
                    updateDisplay("PLAYER 0 IS WINNER")

                }
            }
        else if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[2][0] == boardStatus[1][1])
            {
                if (boardStatus[1][1] == 1) {
                    updateDisplay("PLAYER X IS WINNER")

                } else if (boardStatus[1][1] == 0) {
                    updateDisplay("PLAYER 0 IS WINNER")
                }
            }


    }
    private fun disableButton(){
        for(i in board)
        {
            for(btn in i)
            {
                btn.isEnabled=false
            }
        }
    }

    private fun updateDisplay(s: String) {
        if(s.contains("WINNER"))
        {
            disableButton()
            
        }
    displayTv.text=s

    }

    private fun updateValue(row: Int, col: Int, player: Boolean) {
        var text= if(player) "X" else "0"
        var value=if(player) 1 else 0
    board[row][col].apply {
        isEnabled=false
        setText(text)
    }
        boardStatus[row][col]=value
    }
}

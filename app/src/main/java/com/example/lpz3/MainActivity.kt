package com.example.lpz3

import android.annotation.SuppressLint
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.lpz3.R.layout.activity_main
import kotlinx.android.synthetic.main.activity_main.*
import android.os.CountDownTimer

class MainActivity : AppCompatActivity() {

    private var colorRight : Int = 1
    private var score: Int =0
    private var numberOfQuestions: Int = 0
    private var colorLeft: Int = 1
    // таймер на 1 минуту
    private val timer = object: CountDownTimer(60000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            textView2.text = "Времени осталось 0:" + millisUntilFinished / 1000
        }

        override fun onFinish() {results()}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)
        button.setOnClickListener { yesClick() }
        button2.setOnClickListener{ noClick() }
        generateColors()
        timer.start()
    }


    fun results(){
        button.visibility= Button.INVISIBLE
        button2.visibility= Button.INVISIBLE
        textView6.visibility= TextView.VISIBLE
        textView6.text=("Было задано $numberOfQuestions вопросов из которых Вы правильно ответили на $score")
    }

    private fun noClick(){
        numberOfQuestions++
        if(colorRight!=colorLeft){
            score++
        }
        generateColors()
    }

    private fun yesClick(){
        numberOfQuestions++
        if(colorRight==colorLeft){
            score++
        }
        generateColors()
    }

    private fun rand(from: Int, to: Int) : Int {
        return (from..to).random()
    }

    @SuppressLint("ResourceType", "Recycle")
    fun generateColors() {
        val colors: TypedArray = resources.obtainTypedArray(R.array.rainbow)
        val strings: Array<out String> = resources.getStringArray(R.array.color_name)
        var color=rand(0,6)
        textView4.setTextColor(colors.getColor(color,0))
        color = rand(0,6)
        colorLeft=color
        textView4.text = strings[color]
        color = rand(0,6)
        textView5.setTextColor(colors.getColor(color,0))
        color = rand(0,6)
        textView5.text = strings[color]
        colorRight=color
    }
}

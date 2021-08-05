package com.example.dima

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import java.util.*

class MainActivity: Activity() {
    var dice:ImageView? = null
    var counter:Int = 0
    var timer: Timer? = null
   // var its_run = false
    var imageArray: IntArray= intArrayOf(
        R.drawable.dice_1, R.drawable.dice_2, R.drawable.dice_3,
        R.drawable.dice_4, R.drawable.dice_5, R.drawable.dice_6,
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dice=findViewById(R.id.imageView)

    }
    fun onClikStart(view: View) {
            start()
    }

    fun start(){
        timer = Timer()
        timer?.schedule(object: TimerTask(){
            override fun run() {
                dice?.setImageResource(imageArray[counter])
                counter++
                if (counter == 6) {
                    counter = 0
                    timer?.cancel()
                }
            }
        },0,1000)
    }

}




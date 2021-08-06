package com.example.dima

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import java.util.*


class MainActivity: Activity() {
    private var dice: ImageView? = null
    private var counter: Int = 0
    

    val imageArray: IntArray = intArrayOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dice = findViewById(R.id.diceView)
    }

    fun onClikStart(view: View) {
        view.isVisible = false
        val timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    dice?.setImageResource(imageArray.random())
                    counter++

                    if (counter == 15) {
                        counter = 0
                        timer?.cancel()
                        view.isVisible = true
                    }
                }
            }
        }, 0, 100)

    }
}








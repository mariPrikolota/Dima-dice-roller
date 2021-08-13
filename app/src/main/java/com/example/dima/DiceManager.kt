package com.example.dima

import android.app.Activity
import android.widget.ImageView
import java.util.*

class DiceManager : Activity() {
    fun throwDice(dice: ImageView?, lavelOfPower: Int, comletion: () -> Unit) {
        val timer = Timer()
        var counter = 0

        val imageArray: IntArray = intArrayOf(
            R.drawable.dice_1,
            R.drawable.dice_2,
            R.drawable.dice_3,
            R.drawable.dice_4,
            R.drawable.dice_5,
            R.drawable.dice_6
        )

        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    if (counter == lavelOfPower) {
                        timer.cancel()
                        comletion()
                    }
                    dice?.setImageResource(imageArray.random())
                    counter++
                }
            }
        }, 0, 150)
    }
}
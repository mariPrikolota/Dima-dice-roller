package com.example.dima

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.isVisible
import java.util.*


class MainActivity: Activity() {
    private var dice: ImageView? = null
    private var dice1: ImageView? = null
    private var shkala: ImageView? = null
    private var itsRun = false
    private var counter: Int = 0
    private var counter1: Int = 0
    private val timer1 = Timer()

    val imageArray: IntArray = intArrayOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )
    val imageArray1: IntArray = intArrayOf(
        R.drawable.shkala1,
        R.drawable.shkala2,
        R.drawable.shkala3,
        R.drawable.shkala4,
        R.drawable.shkala5,
        R.drawable.shkala6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dice = findViewById(R.id.diceView)
        dice1 = findViewById(R.id.diceView1)
        shkala = findViewById(R.id.shkalaView)

    }

    fun onClikStart(view: View) {

        view as ImageButton
        if (!itsRun) {
            start()
            view.setImageResource(R.drawable.rull)
            itsRun = true
        } else  {
            view.isVisible = false
            timer1.cancel()
            view.setImageResource(R.drawable.start_1)
            rull()
            itsRun = false
            view.isVisible = true

        }
    }

    fun start(){
        timer1.schedule(
            object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        shkala?.setImageResource(imageArray1[counter1])
                        counter1++
                        if (counter1 == 6) counter1 = 0
                    }
                }
            },
            0,
            400
        )
    }

         fun rull() {
             val timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        dice?.setImageResource(imageArray.random())
                        dice1?.setImageResource(imageArray.random())
                        counter ++
                        if (counter == counter1 ) {
                            counter = 0
                            timer.cancel()
                        }
                    }
                }
            }, 0, 500)
        }

    }








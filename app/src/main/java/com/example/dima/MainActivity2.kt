package com.example.dima

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.view.isVisible
import java.util.*

enum class ButtonState2 {
    IsStoped, IsStarted, IsHidden
}

class MainActivity2 : Activity() {
    private var dice: ImageView? = null
    private var progress: ImageView? = null
    private var progressLavel = 0
    private var progressIsGrowing = true
    private var progressTimer = Timer()
    private var buttonState = ButtonState2.IsStoped

    private val imageArray: IntArray = intArrayOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    private val imageArray1: IntArray = intArrayOf(
        R.drawable.shkala1,
        R.drawable.shkala2,
        R.drawable.shkala3,
        R.drawable.shkala4,
        R.drawable.shkala5,
        R.drawable.shkala6
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        dice = findViewById(R.id.diceView3)
        progress = findViewById(R.id.shkalaView2)
    }

    fun onClickStart(view: View) {
        when (buttonState) {
            ButtonState2.IsStoped -> {
                startProgressLoading()
                setupButton(ButtonState2.IsStarted)
            }
            ButtonState2.IsStarted -> {
                setupButton(ButtonState2.IsHidden)
                progressTimer.cancel()
                throwDices(progressLavel)
                progressLavel = 0
                progressIsGrowing = true
            }
            ButtonState2.IsHidden -> println("Current state isHidden")
        }
    }

    private fun startProgressLoading() {
        progressTimer = Timer()

        progressTimer.schedule(
            object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        if (progressLavel == 5) progressIsGrowing = false
                        if (progressLavel == 0) progressIsGrowing = true
                        if (progressIsGrowing) progressLavel++ else progressLavel--
                        progress?.setImageResource(imageArray1[progressLavel])
                    }
                }
            },
            0,
            500
        )
    }

    private fun throwDices(lavelOfPower: Int) {
        throwDice(dice, lavelOfPower)
    }

    private fun throwDice(dice: ImageView?, lavelOfPower: Int) {
        val timer = Timer()
        var counter = 0

        timer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    if (counter == lavelOfPower) {
                        timer.cancel()
                        setupButton(ButtonState2.IsStoped)
                    }
                    dice?.setImageResource(imageArray.random())
                    counter++
                }
            }
        }, 0, 150)
    }

    private fun setupButton(state: ButtonState2) {
        val imageButton = findViewById<ImageButton>(R.id.imageButton2)

        when (state) {
            ButtonState2.IsStoped -> {
                imageButton.setImageResource(R.drawable.start_1)
                imageButton.isVisible = true
            }
            ButtonState2.IsStarted -> imageButton.setImageResource(R.drawable.rull)
            ButtonState2.IsHidden -> imageButton.isVisible = false
        }

        buttonState = state
    }
}

@file:Suppress("DEPRECATION")

package com.example.dima

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.two_dices_menu.*
import kotlinx.android.synthetic.main.two_dices_activity.*
import kotlinx.android.synthetic.main.two_dices_menu.two_dices_menu_drawer_layout
import java.util.*

class TwoDicesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var progressLavel = 0
    private var progressIsGrowing = true
    private var progressTimer = Timer()
    private var buttonState = ButtonState.IsStoped

//    private val imageArray: IntArray = intArrayOf(
//        R.drawable.dice_1,
//        R.drawable.dice_2,
//        R.drawable.dice_3,
//        R.drawable.dice_4,
//        R.drawable.dice_5,
//        R.drawable.dice_6
//    )

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
        setContentView(R.layout.two_dices_menu)

        menuTwoDices.setNavigationItemSelectedListener(this)
    }

    fun onClickStart(view: View) {
        when (buttonState) {
            ButtonState.IsStoped -> {
                startProgressLoading()
                setupButton(ButtonState.IsStarted)
            }
            ButtonState.IsStarted -> {
                setupButton(ButtonState.IsHidden)
                progressTimer.cancel()
                throwDices(progressLavel) {
                    setupButton(ButtonState.IsStoped)
                }

                progressLavel = 0
                progressIsGrowing = true
            }
            ButtonState.IsHidden -> println("Current state isHidden")
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
                        shkalaView?.setImageResource(imageArray1[progressLavel])
                    }
                }
            },
            0,
            500
        )
    }

    private fun throwDices(lavelOfPower: Int, comletion: () -> Unit) {
        val diceManager = DiceManager()

        diceManager.throwDice(diceView, lavelOfPower, comletion)
        diceManager.throwDice(diceView1, lavelOfPower, comletion)
    }

    private fun setupButton(state2: ButtonState) {
        val imageButton = findViewById<ImageButton>(R.id.imageButton)

        when (state2) {
            ButtonState.IsStoped -> {
                imageButton.setImageResource(R.drawable.start_1)
                imageButton.isVisible = true
            }
            ButtonState.IsStarted -> imageButton.setImageResource(R.drawable.rull)
            ButtonState.IsHidden -> imageButton.isVisible = false
        }

        buttonState = state2
    }

    fun onClicRun(view: View){
        val intent = Intent(this, TitleActivity::class.java)
        startActivity(intent)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_one_dice -> {
                val intent = Intent(this, OneDiceActivity::class.java)
                startActivityForResult(intent, 2)
            }
            R.id.id_razrab -> {
                val intent = Intent(this, DevelopersActivity::class.java)
                startActivityForResult(intent, 2)
            }
            else -> println("Another menu item")
        }

        two_dices_menu_drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }
}
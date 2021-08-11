package com.example.dima

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main_menu.*


class MenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        menuDice.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.id_one_dice -> {
                val intent1 = Intent(this, MainActivity2::class.java)
                startActivity(intent1)
            }
            R.id.id_two_dice ->{
                val intent2 = Intent(this, MainActivity::class.java)
                startActivity(intent2)
            }
        }
        return true
    }
}
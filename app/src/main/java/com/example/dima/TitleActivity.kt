@file:Suppress("DEPRECATION")

package com.example.dima

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.title_menu.*

class TitleActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.title_menu)

        menuTitle.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.id_one_dice -> {
                val intent = Intent(this, OneDiceActivity::class.java)
                startActivityForResult(intent, 2)
            }
            R.id.id_two_dice -> {
                val  intent = Intent(this, TwoDicesActivity::class.java)
                startActivityForResult(intent, 2)
            }
            R.id.id_razrab -> {
                val  intent = Intent(this, DevelopersActivity::class.java)
                startActivityForResult(intent, 2)
            }
            else -> println("Another menu item")
        }

        title_drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }
}
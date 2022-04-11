package com.example.cse225_android_kotlin_2022


import android.os.Bundle

import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity

import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class NavigationDrawerExample : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{

   lateinit var  drawer: DrawerLayout
   lateinit var navigationView: NavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer_example)
        var toolbar:Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        var actionBarDrawerToggle = ActionBarDrawerToggle(this,drawer,toolbar,
            R.string.navigation_drawer_open,R.string.navigation_drawer_close)

        drawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MessageFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_message)
        }
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.nav_message -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,MessageFragment())
                .commit()

        R.id.nav_chat -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,ChatFragment())
            .commit()

        R.id.nav_profile -> supportFragmentManager.beginTransaction().replace(R.id.fragment_container,ProfileFragment())
            .commit()

        R.id.nav_share -> Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
        R.id.nav_send -> Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show()
    }
        drawer.closeDrawer(GravityCompat.START)
        return true

    }
}
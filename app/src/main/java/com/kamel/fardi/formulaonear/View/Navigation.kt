package com.kamel.fardi.formulaonear.View


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kamel.fardi.formulaonear.View.Fragement.*
import com.kamel.fardi.formulaonear.R
import com.kamel.fardi.formulaonear.texts

class Navigation : AppCompatActivity() {
    lateinit var BottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation)
        //
        BottomNavigationView = findViewById(R.id.bottomNavigationView)
        //
        val toolbar: Toolbar = findViewById(R.id.toolbar54)
        toolbar.setTitle("")
        setSupportActionBar(toolbar)

        toolbar.visibility = View.GONE
        ///////////////////////////////////////////////////////////////////////////////////////////
        supportFragmentManager.beginTransaction().replace(R.id.container, FragmentHome()).commit();
        BottomNavigationView.setSelectedItemId(R.id.nav_home)
        BottomNavigationView.setOnItemSelectedListener { item ->
            var selectedFragment: Fragment? = null
            when (item.itemId) {
                R.id.nav_home -> {
                    toolbar.visibility = View.GONE
                    selectedFragment = texts()
                }
                R.id.nav_explore -> {
                    toolbar.visibility = View.GONE
                    selectedFragment = FragmentHome()
                }
                R.id.nav_add -> {
                    toolbar.visibility = View.GONE
                    BottomNavigationView.visibility = View.GONE
                    selectedFragment = AddFragment()
                }
                R.id.nav_favorite -> {
                    toolbar.visibility = View.VISIBLE
                    selectedFragment = FragmentHome()
                }
                R.id.nav_profile -> {
                    toolbar.visibility = View.GONE

                    selectedFragment = ProfileFragment()
                }
            }
            val transaction = supportFragmentManager.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.slide_in,
                R.anim.slide_out
            )
            transaction.replace(R.id.container, selectedFragment!!)
            transaction.commit()
            true
        }
        ///////////////////////////////////////////////////////////////////////////////////////////
    }



}
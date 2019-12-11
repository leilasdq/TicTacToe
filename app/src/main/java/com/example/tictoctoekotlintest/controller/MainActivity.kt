package com.example.tictoctoekotlintest.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tictoctoekotlintest.BoardFragment
import com.example.tictoctoekotlintest.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentById(R.id.fragment_container)

        if(fragment == null) {
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container, BoardFragment.newInstance()).commit()
        }
    }
}

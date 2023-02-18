package com.battousai.gamelist.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.battousai.gamelist.R
import com.battousai.gamelist.managers.NavigationManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationManager.shared.launch(this@MainActivity)
    }
}
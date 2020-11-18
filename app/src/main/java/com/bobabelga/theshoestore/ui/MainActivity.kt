package com.bobabelga.theshoestore.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bobabelga.theshoestore.R
import com.bobabelga.theshoestore.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var backPressedTime: Long = 0
    private var backToast: Toast? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Timber.plant(Timber.DebugTree())

        setSupportActionBar(binding.toolbar)
    }
    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis())
        {
            backToast?.cancel()
            super.onBackPressed()
            return
        }
        else
        {
            backToast = Toast.makeText(
                getBaseContext(),
                "Press back again to exit",
                Toast.LENGTH_SHORT
            )
            backToast?.show()
        }
        backPressedTime = System.currentTimeMillis()
    }
}
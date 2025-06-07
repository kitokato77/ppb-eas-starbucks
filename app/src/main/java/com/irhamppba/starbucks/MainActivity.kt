package com.irhamppba.starbucks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnGetStarted: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetStarted = findViewById(R.id.btnGetStarted)

        btnGetStarted.setOnClickListener {
            startActivity(Intent(this, TermsActivity::class.java))
        }
    }
}
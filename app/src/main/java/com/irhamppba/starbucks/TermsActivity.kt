package com.irhamppba.starbucks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TermsActivity : AppCompatActivity() {
    private lateinit var btnAgree: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms)

        btnAgree = findViewById(R.id.btnAgree)

        btnAgree.setOnClickListener {
            startActivity(Intent(this, PhoneVerificationActivity::class.java))
        }
    }
}
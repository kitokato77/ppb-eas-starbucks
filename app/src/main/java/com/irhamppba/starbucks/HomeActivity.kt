package com.irhamppba.starbucks

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var tvUserName: TextView
    private lateinit var btnExplore: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        tvUserName = findViewById(R.id.tvUserName)
        btnExplore = findViewById(R.id.btnExplore)

        // Get user data from intent
        val fullName = intent.getStringExtra("full_name") ?: "User"
        val email = intent.getStringExtra("email") ?: ""
        val favoriteMenu = intent.getStringExtra("favorite_menu") ?: ""
        val referralCode = intent.getStringExtra("referral_code") ?: ""

        // Display user name
        tvUserName.text = fullName

        btnExplore.setOnClickListener {
            Toast.makeText(this, "Fitur menu akan segera hadir!", Toast.LENGTH_SHORT).show()
        }

        // Show welcome message with user info
        showWelcomeMessage(fullName, favoriteMenu)
    }

    private fun showWelcomeMessage(name: String, favoriteMenu: String) {
        val message = if (favoriteMenu.isNotEmpty()) {
            "Selamat datang $name! Kami lihat Anda suka $favoriteMenu. Reward Rp10.000 sudah aktif!"
        } else {
            "Selamat datang $name! Reward Rp10.000 sudah aktif!"
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onBackPressed() {
        // Disable back button to prevent going back to registration
        Toast.makeText(this, "Selamat datang di Starbucks!", Toast.LENGTH_SHORT).show()
    }
}
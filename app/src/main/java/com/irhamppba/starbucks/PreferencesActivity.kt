package com.irhamppba.starbucks

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class PreferencesActivity : AppCompatActivity() {
    private lateinit var etFavoriteMenu: EditText
    private lateinit var etReferralCode: EditText
    private lateinit var btnSignUp: Button
    private var isFormValid = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)

        etFavoriteMenu = findViewById(R.id.etFavoriteMenu)
        etReferralCode = findViewById(R.id.etReferralCode)
        btnSignUp = findViewById(R.id.btnSignUp)

        // Enable button initially since preferences are optional
        enableSignUpButton()

        // Add text watcher to update button state
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                enableSignUpButton()
            }
        }

        etFavoriteMenu.addTextChangedListener(textWatcher)
        etReferralCode.addTextChangedListener(textWatcher)

        btnSignUp.setOnClickListener {
            completeRegistration()
        }
    }

    private fun enableSignUpButton() {
        btnSignUp.isEnabled = true
        btnSignUp.setBackgroundColor(ContextCompat.getColor(this, R.color.starbucks_green))
        isFormValid = true
    }

    private fun completeRegistration() {
        if (!isFormValid) return

        Toast.makeText(this, "Registrasi berhasil! Selamat datang di Starbucks!", Toast.LENGTH_LONG).show()

        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("full_name", getIntent().getStringExtra("full_name"))
        intent.putExtra("email", getIntent().getStringExtra("email"))
        intent.putExtra("favorite_menu", etFavoriteMenu.text.toString().trim())
        intent.putExtra("referral_code", etReferralCode.text.toString().trim())

        // Clear back stack to prevent going back to registration flow
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
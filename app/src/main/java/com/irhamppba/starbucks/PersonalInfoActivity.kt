package com.irhamppba.starbucks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PersonalInfoActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etFullName: EditText
    private lateinit var etBirthDate: EditText
    private lateinit var btnNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_info)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        etFullName = findViewById(R.id.etFullName)
        etBirthDate = findViewById(R.id.etBirthDate)
        btnNext = findViewById(R.id.btnNext)

        btnNext.setOnClickListener {
            if (validateForm()) {
                val intent = Intent(this, PreferencesActivity::class.java)
                intent.putExtra("phone_number", getIntent().getStringExtra("phone_number"))
                intent.putExtra("email", etEmail.text.toString().trim())
                intent.putExtra("full_name", etFullName.text.toString().trim())
                intent.putExtra("birth_date", etBirthDate.text.toString().trim())
                startActivity(intent)
            }
        }
    }

    private fun validateForm(): Boolean {
        val email = etEmail.text.toString().trim()
        val password = etPassword.text.toString().trim()
        val fullName = etFullName.text.toString().trim()
        val birthDate = etBirthDate.text.toString().trim()

        when {
            email.isEmpty() -> {
                Toast.makeText(this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return false
            }
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Toast.makeText(this, "Format email tidak valid", Toast.LENGTH_SHORT).show()
                return false
            }
            password.isEmpty() -> {
                Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return false
            }
            password.length < 8 -> {
                Toast.makeText(this, "Password minimal 8 karakter", Toast.LENGTH_SHORT).show()
                return false
            }
            fullName.isEmpty() -> {
                Toast.makeText(this, "Nama lengkap tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return false
            }
            birthDate.isEmpty() -> {
                Toast.makeText(this, "Tanggal lahir tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }
}
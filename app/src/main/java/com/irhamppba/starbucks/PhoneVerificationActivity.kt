package com.irhamppba.starbucks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PhoneVerificationActivity : AppCompatActivity() {
    private lateinit var etPhoneNumber: EditText
    private lateinit var btnSendOTP: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)

        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        btnSendOTP = findViewById(R.id.btnSendOTP)

        btnSendOTP.setOnClickListener {
            val phoneNumber = etPhoneNumber.text.toString().trim()

            if (phoneNumber.isEmpty()) {
                Toast.makeText(this, "Masukkan nomor telepon", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (phoneNumber.length < 10) {
                Toast.makeText(this, "Nomor telepon tidak valid", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simulate OTP sending
            Toast.makeText(this, "Kode OTP telah dikirim ke $phoneNumber", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, OtpVerificationActivity::class.java)
            intent.putExtra("phone_number", phoneNumber)
            startActivity(intent)
        }
    }
}
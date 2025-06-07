package com.irhamppba.starbucks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class OtpVerificationActivity : AppCompatActivity() {
    private lateinit var tvPhoneNumber: TextView
    private lateinit var etOtpCode: EditText
    private lateinit var btnVerifyOTP: Button
    private val defaultOtpCode = "123456" // Default OTP untuk testing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)

        tvPhoneNumber = findViewById(R.id.tvPhoneNumber)
        etOtpCode = findViewById(R.id.etOtpCode)
        btnVerifyOTP = findViewById(R.id.btnVerifyOTP)

        // Get phone number from previous activity
        val phoneNumber = intent.getStringExtra("phone_number") ?: ""
        val maskedPhone = maskPhoneNumber(phoneNumber)
        tvPhoneNumber.text = maskedPhone

        btnVerifyOTP.setOnClickListener {
            val enteredOtp = etOtpCode.text.toString().trim()

            if (enteredOtp.isEmpty()) {
                Toast.makeText(this, "Masukkan kode OTP", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (enteredOtp.length != 6) {
                Toast.makeText(this, "Kode OTP harus 6 digit", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verify OTP (using default code for testing)
            if (enteredOtp == defaultOtpCode) {
                Toast.makeText(this, "Verifikasi berhasil!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, PersonalInfoActivity::class.java)
                intent.putExtra("phone_number", phoneNumber)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Kode OTP salah. Gunakan: $defaultOtpCode", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun maskPhoneNumber(phoneNumber: String): String {
        return if (phoneNumber.length > 4) {
            val prefix = phoneNumber.substring(0, 4)
            val suffix = phoneNumber.substring(phoneNumber.length - 4)
            "$prefix****$suffix"
        } else {
            phoneNumber
        }
    }
}
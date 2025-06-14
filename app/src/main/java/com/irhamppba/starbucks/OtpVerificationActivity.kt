package com.irhamppba.starbucks

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class OtpVerificationActivity : AppCompatActivity() {

    private lateinit var tvPhoneNumber: TextView
    private lateinit var etOtp1: EditText
    private lateinit var etOtp2: EditText
    private lateinit var etOtp3: EditText
    private lateinit var etOtp4: EditText
    private lateinit var btnVerifyOTP: Button

    private val defaultOtpCode = "1234" // disesuaikan dengan 4 kolom OTP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)

        // Init views
        tvPhoneNumber = findViewById(R.id.tvPhoneNumber)
        etOtp1 = findViewById(R.id.etOtp1)
        etOtp2 = findViewById(R.id.etOtp2)
        etOtp3 = findViewById(R.id.etOtp3)
        etOtp4 = findViewById(R.id.etOtp4)
        btnVerifyOTP = findViewById(R.id.btnVerifyOTP)

        // Set phone number
        val phoneNumber = intent.getStringExtra("phone_number") ?: ""
        tvPhoneNumber.text = maskPhoneNumber(phoneNumber)

        // Auto move cursor to next input
        setupOtpAutoMove()

        // Button click
        btnVerifyOTP.setOnClickListener {
            val enteredOtp = etOtp1.text.toString() +
                    etOtp2.text.toString() +
                    etOtp3.text.toString() +
                    etOtp4.text.toString()

            if (enteredOtp.length != 4) {
                Toast.makeText(this, "Masukkan 4 digit kode OTP", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

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

    private fun setupOtpAutoMove() {
        etOtp1.addTextChangedListener(OtpTextWatcher(etOtp1, etOtp2))
        etOtp2.addTextChangedListener(OtpTextWatcher(etOtp2, etOtp3))
        etOtp3.addTextChangedListener(OtpTextWatcher(etOtp3, etOtp4))
    }

    private fun maskPhoneNumber(phoneNumber: String): String {
        return if (phoneNumber.length > 4) {
            val prefix = phoneNumber.substring(0, 4)
            val suffix = phoneNumber.takeLast(4)
            "$prefix****$suffix"
        } else {
            phoneNumber
        }
    }

    private inner class OtpTextWatcher(
        private val currentEditText: EditText,
        private val nextEditText: EditText?
    ) : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s?.length == 1) {
                nextEditText?.requestFocus()
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}

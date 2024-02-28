package com.vicksam.ferapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val goToSignUp = findViewById<TextView>(R.id.go_to_signup_tv)
        val loginBtn = findViewById<Button>(R.id.login_button_id)
        loginBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(LoginActivity@this, MainActivity::class.java)
            startActivity(intent)
        })
        goToSignUp.setOnClickListener(View.OnClickListener {
            val intent = Intent(LoginActivity@this, SignUpActivity::class.java)
            startActivity(intent)
        })
    }
}
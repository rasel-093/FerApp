package com.vicksam.ferapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicksam.ferapp.db.user.User
import com.vicksam.ferapp.db.user.UserViewModel
import com.vicksam.ferapp.db.user.UserViewModelFactory

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userViewModel: UserViewModel = ViewModelProvider(this, UserViewModelFactory(application)).get(UserViewModel::class.java)

        setContentView(R.layout.activity_sign_up)
        val nameEt = findViewById<EditText>(R.id.name_et)
        val userNameEt = findViewById<EditText>(R.id.username_et)
        val ageEt = findViewById<EditText>(R.id.age_et)
        val passwordEt = findViewById<EditText>(R.id.password_field)
        val confirmPasswordEt = findViewById<EditText>(R.id.confirm_password_field)
        val genderEt = findViewById<EditText>(R.id.gender_field)
        val signupBtn = findViewById<Button>(R.id.signup_buttonId)

        signupBtn.setOnClickListener {
            val name = nameEt.text.toString()
            val userName = userNameEt.text.toString()
            val age = ageEt.text.toString().toInt()
            val password  = passwordEt.text.toString()
            val confirmPassword = confirmPasswordEt.text.toString()
            val gender = genderEt.text.toString()

            val user = User(
                age = age,
                name = name,
                gender = gender,
                username = userName,
                password = password
            )
            userViewModel.insertUser(user)
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()
        }
    }
}

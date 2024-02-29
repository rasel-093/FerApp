package com.vicksam.ferapp

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.vicksam.ferapp.db.user.UserViewModel
import com.vicksam.ferapp.db.user.UserViewModelFactory
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val userViewModel: UserViewModel = ViewModelProvider(this, UserViewModelFactory(application)).get(UserViewModel::class.java)
        val userNameEt = findViewById<EditText>(R.id.username_lg)
        val passwordEt = findViewById<EditText>(R.id.password_lg)
        val goToSignUp = findViewById<TextView>(R.id.go_to_signup_tv)
        val loginBtn = findViewById<Button>(R.id.login_button_id)
        loginBtn.setOnClickListener(View.OnClickListener {
            val userName = userNameEt.text.toString()
            val password  = passwordEt.text.toString()
            var userPassword:String=""
            if(userName.isNotEmpty()&&password.isNotEmpty()){
                userViewModel.viewModelScope.launch {
                    userViewModel.getUser(userName)
                    userPassword = userViewModel.user.password
                }
                if(password == userPassword){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this, "Wrong Credential", Toast.LENGTH_SHORT).show()
                }

            }
            else{
                Toast.makeText(this, "Fill the Form properly", Toast.LENGTH_SHORT).show()
            }

        })
        goToSignUp.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        })
    }
}



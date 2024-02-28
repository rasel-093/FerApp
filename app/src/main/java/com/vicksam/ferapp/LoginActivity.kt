package com.vicksam.ferapp

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vicksam.ferapp.db.user.UserViewModel

class LoginActivity : AppCompatActivity() {
    //private lateinit var userViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //val viewModel = ViewModelProvider(this, YourViewModelFactory(application)).get(UserViewModel::class.java)
        //val myViewModel1 = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)
        //viewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //val userViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(UserViewModel::class.java)
        //val myViewModelWithParm: UserViewModel by viewModels { MyViewModelFactory(application) }

        val goToSignUp = findViewById<TextView>(R.id.go_to_signup_tv)
        val loginBtn = findViewById<Button>(R.id.login_button_id)
        loginBtn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })
        goToSignUp.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        })
    }
}


//class YourViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
//            return UserViewModel(application) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}

// Inside Activity or Fragment


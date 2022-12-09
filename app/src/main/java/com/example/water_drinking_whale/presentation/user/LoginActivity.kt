package com.example.water_drinking_whale.presentation.user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.water_drinking_whale.R
import com.example.water_drinking_whale.databinding.ActivityLoginBinding
import com.example.water_drinking_whale.presentation.main.MainActivity
import com.example.water_drinking_whale.utils.NetworkState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    private var backWaitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.apply {
            loginBtn.setOnClickListener { login() }
            loginFindPasswordBtn.setOnClickListener { }
            loginSignUpBtn.setOnClickListener {
                startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
            }
        }
        observeNetworkState()
    }

    private fun login() {
        val username = binding.loginIdEt.text.toString()
        val password = binding.loginPasswordEt.text.toString()

        when {
            username == "" -> Toast.makeText(this, "아이디를 입력해 주세요", Toast.LENGTH_SHORT).show()
            password == "" -> Toast.makeText(this, "비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show()
            else -> {
                viewModel.login(username = username, password = password)
            }
        }
    }

    private fun observeNetworkState() {
        viewModel.run {
            networkState.observe(this@LoginActivity) { networkState ->
                when (networkState) {
                    NetworkState.SUCCESS -> {
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                    NetworkState.FAILURE -> {
                        Toast.makeText(this@LoginActivity, "아이디 또는 비밀번호를 확인해 주세요", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() - backWaitTime >= 2000) {
            backWaitTime = System.currentTimeMillis()
            Toast.makeText(this, "뒤로가기 버튼을 한번 더 누르면 종료됩니다", Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }
}

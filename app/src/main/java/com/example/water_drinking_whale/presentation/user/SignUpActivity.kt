package com.example.water_drinking_whale.presentation.user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.water_drinking_whale.R
import com.example.water_drinking_whale.databinding.ActivitySignUpBinding
import com.example.water_drinking_whale.presentation.main.MainActivity
import com.example.water_drinking_whale.utils.NetworkState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        binding.signupBtn.setOnClickListener { signUp() }
        observeNetworkState()
    }

    private fun signUp() {
        val username = binding.signupIdEt.text.toString()
        val password = binding.signupPasswordEt.text.toString()
        val name = binding.signupNameEt.text.toString()

        when {
            username == "" -> Toast.makeText(this, "아이디를 입력해 주세요", Toast.LENGTH_SHORT).show()
            password == "" -> Toast.makeText(this, "비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show()
            name == "" -> Toast.makeText(this, "이름을 입력해 주세요", Toast.LENGTH_SHORT).show()
            else -> {
                viewModel.signUp(username = username, password = password, name = name)
            }
        }
    }

    private fun observeNetworkState() {
        viewModel.run {
            networkState.observe(this@SignUpActivity) { networkState ->
                when (networkState) {
                    NetworkState.SUCCESS -> {
                        startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                        finish()
                    }
                    NetworkState.FAILURE -> {
                        Toast.makeText(this@SignUpActivity, "회원가입 실패..", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }
        }
    }
}

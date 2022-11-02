package com.example.water_drinking_whale.presentation.mypage

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.water_drinking_whale.R
import com.example.water_drinking_whale.databinding.ActivityMyPageBinding

class MyPageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_page)

        binding.editNameBtn.setOnClickListener {
            val intent = Intent(this, EditNameActivity::class.java)
            startActivity(intent)
        }

        binding.editPasswordBtn.setOnClickListener {
            val intent = Intent(this, EditPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.editWeightBtn.setOnClickListener {
            val intent = Intent(this, EditWeightActivity::class.java)
            startActivity(intent)
        }
    }
}

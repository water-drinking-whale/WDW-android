package com.example.water_drinking_whale.presentation.notice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.water_drinking_whale.R
import com.example.water_drinking_whale.databinding.ActivityMainBinding
import com.example.water_drinking_whale.databinding.ActivityNoticeBinding

class NoticeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoticeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeBinding.inflate(layoutInflater)   // 바인딩 객체 획득
        setContentView(binding.root)
    }
}
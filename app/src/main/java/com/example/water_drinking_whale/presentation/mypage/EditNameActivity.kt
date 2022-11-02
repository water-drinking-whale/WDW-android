package com.example.water_drinking_whale.presentation.mypage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.water_drinking_whale.R
import com.example.water_drinking_whale.databinding.ActivityEditNameBinding

class EditNameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_name)
    }
}

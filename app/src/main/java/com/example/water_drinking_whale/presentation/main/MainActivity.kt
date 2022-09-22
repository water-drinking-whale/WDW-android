package com.example.water_drinking_whale.presentation.main

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.water_drinking_whale.R
import com.example.water_drinking_whale.databinding.ActivityMainBinding
import com.example.water_drinking_whale.databinding.DialogHomeBinding
import com.example.water_drinking_whale.presentation.award.AwardActivity
import com.example.water_drinking_whale.presentation.log.LogActivity
import com.example.water_drinking_whale.presentation.mypage.MyPageActivity
import com.example.water_drinking_whale.presentation.notice.NoticeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isFabOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setToolbar()
        setFabClickEvent()
    }

    private fun setFabClickEvent() {
        with(binding) {
            mainFab.setOnClickListener { toggleFab() }
            noticeFab.setOnClickListener {
                startActivity(Intent(this@MainActivity, NoticeActivity::class.java))
            }
            logFab.setOnClickListener {
                startActivity(Intent(this@MainActivity, LogActivity::class.java))
            }
            awardFab.setOnClickListener {
                startActivity(Intent(this@MainActivity, AwardActivity::class.java))
            }
            myPageFab.setOnClickListener {
                startActivity(Intent(this@MainActivity, MyPageActivity::class.java))
            }
        }
    }

    private fun toggleFab() {
        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.logFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.noticeFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.awardFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.myPageFab, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.mainFab, View.ROTATION, 45f, 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(binding.logFab, "translationY", -720f).apply { start() }
            ObjectAnimator.ofFloat(binding.noticeFab, "translationY", -540f).apply { start() }
            ObjectAnimator.ofFloat(binding.awardFab, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.myPageFab, "translationY", -180f).apply { start() }
            ObjectAnimator.ofFloat(binding.mainFab, View.ROTATION, 0f, 45f).apply { start() }
        }

        isFabOpen = !isFabOpen
    }

    private fun setToolbar() {
        binding.mainToolbar.apply {
            inflateMenu(R.menu.main_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.homeSetting -> {
                        startActivity(Intent(this@MainActivity, MyPageActivity::class.java))
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setWaterIntakeDialog() {
        val dialogBinding = DialogHomeBinding.inflate(layoutInflater)
        val intakeEt = dialogBinding.dialogEt

        val builder = AlertDialog.Builder(this)
        builder.apply {
            setMessage(R.string.home_today_intake)
            setView(dialogBinding.root)
            setCancelable(false)
            setPositiveButton(R.string.positive_btn, null)
            setNegativeButton(R.string.negative_btn, null)
        }.create().apply {
            show()
            getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val todayIntake = binding.mainTodayIntakeTv
                if (intakeEt.text.toString() == "") {
                    Toast.makeText(context, "물 섭취량을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    val total = todayIntake.text.toString().toInt() + intakeEt.text.toString().toInt()
                    todayIntake.text = total.toString()
                    // updateDB(total)
                    dismiss()
                }
            }
        }
    }
}

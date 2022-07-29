package com.example.water_drinking_whale


import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


import com.example.water_drinking_whale.databinding.ActivityMainBinding
import com.example.water_drinking_whale.presentation.award.AwardActivity
import com.example.water_drinking_whale.presentation.log.LogActivity
import com.example.water_drinking_whale.presentation.mypage.MyPageActivity
import com.example.water_drinking_whale.presentation.notice.NoticeActivity


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var isFabOpen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)   // 바인딩 객체 획득
        setContentView(binding.root)        // 액티비티 화면 출력 or getRoot()

        setFABClickEvent()

    }

    private fun setFABClickEvent() {
        binding.mainFab.setOnClickListener{
            toggleFab()
        }


        binding.noticeFab.setOnClickListener {
            intent = Intent(this, NoticeActivity::class.java)
            startActivity(intent)
        }


        binding.logFab.setOnClickListener {
            intent = Intent(this, LogActivity::class.java)
            startActivity(intent)
        }

        binding.awardFab.setOnClickListener {
            intent = Intent(this, AwardActivity::class.java)
            startActivity(intent)
        }

        binding.myPageFab.setOnClickListener {
            intent = Intent(this, MyPageActivity::class.java)
            startActivity(intent)
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






}
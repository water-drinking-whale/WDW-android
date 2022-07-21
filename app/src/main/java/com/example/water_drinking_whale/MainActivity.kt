package com.example.water_drinking_whale

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.water_drinking_whale.databinding.ActivityMainBinding
import com.example.water_drinking_whale.presentation.home.HomeFragment
import com.example.water_drinking_whale.presentation.log.LogFragment
import com.example.water_drinking_whale.presentation.notice.NoticeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // 바인딩 객체 획득
        setContentView(binding.root) // 액티비티 화면 출력 or getRoot()

        initBottomNavigation()
    }

    // BottomNavigation 초기화 및 선택 버튼 설정 함수
    private fun initBottomNavigation() {
        binding.bottomNavigation.run {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottomNavHome -> changeFragment(HomeFragment())
                    R.id.bottomNavLog -> changeFragment(LogFragment())
                    R.id.bottomNavNotice -> changeFragment(NoticeFragment())
                }
                true
            }
            selectedItemId = R.id.bottomNavHome // 초기값 세팅
        }
    }

    // fragment 변경 함수
    private fun changeFragment(fragment: Fragment) {
        with(supportFragmentManager.beginTransaction()) {
            replace(binding.mainFrameLayout.id, fragment)
            commit()
        }
    }
}

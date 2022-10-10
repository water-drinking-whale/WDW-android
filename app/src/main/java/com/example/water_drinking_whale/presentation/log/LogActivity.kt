package com.example.water_drinking_whale.presentation.log

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.water_drinking_whale.R
import com.example.water_drinking_whale.databinding.ActivityLogBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class LogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogBinding

    // 년월 변수
    lateinit var selectedDate: LocalDate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)

        // binding 초기화
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log)

        // 화면 설정
        setMonthView()

        // 이전달 버튼 이벤트
        binding.preBtn.setOnClickListener {
            // 현재 월 -1 변수에 담기
            CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1)
            setMonthView()
        }

        // 다음달 버튼 이벤트
        binding.nextBtn.setOnClickListener {
            CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1)
            setMonthView()
        }

        binding.chartBtn.setOnClickListener {
            val intent = Intent(this, ChartActivity::class.java)
            startActivity(intent)
        }
    }

    // 날짜 화면에 보여주기
    private fun setMonthView() {
        // 년월 텍스트뷰 셋팅
        binding.monthYearText.text = monthYearFromDate(CalendarUtil.selectedDate)

        // 날짜 생성해서 리스트에 담기
        val dayList = dayInMonthArray(CalendarUtil.selectedDate)

        // 어댑터 초기화
        val adapter = CalendarAdapter(dayList)

        // 레이아웃 설정(열 7개)
        var manager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 7)

        // 레이아웃 적용
        binding.recyclerView.layoutManager = manager

        // 어댑터 적용
        binding.recyclerView.adapter = adapter
    }

    // 날짜 타입 설정
    private fun monthYearFromDate(date: LocalDate): String {

        var formatter = DateTimeFormatter.ofPattern("MM월 yyyy")

        // 받아온 날짜를 해당 포맷으로 변경
        return date.format(formatter)
    }

    private fun yearMonthFromDate(date: LocalDate): String {
        var formatter = DateTimeFormatter.ofPattern("yyyy년 MM월")

        return date.format(formatter)
    }

    // 날짜 생성
    private fun dayInMonthArray(date: LocalDate): ArrayList<LocalDate?> {

        var dayList = ArrayList<LocalDate?>()

        var yearMonth = YearMonth.from(date)

        // 해당 월 마지막 날짜 가져오기(예: 28, 30, 31)
        var lastDay = yearMonth.lengthOfMonth()

        // 해당 월의 첫 번째 날 가져오기(예: 4월 1일)
        var firstDay = CalendarUtil.selectedDate.withDayOfMonth(1)

        // 첫 번째날 요일 가져오기(월:1, 일: 7)
        var dayOfWeek = firstDay.dayOfWeek.value

        for (i in 1..41) {
            if (i <= dayOfWeek || i > (lastDay + dayOfWeek)) {
                dayList.add(null)
            } else {
                dayList.add(LocalDate.of(CalendarUtil.selectedDate.year, CalendarUtil.selectedDate.monthValue, i - dayOfWeek))
            }
        }

        return dayList
    }
}

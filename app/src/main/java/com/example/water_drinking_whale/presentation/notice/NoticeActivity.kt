package com.example.water_drinking_whale.presentation.notice

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_drinking_whale.data.database.AppDatabase
import com.example.water_drinking_whale.data.database.Notice
import com.example.water_drinking_whale.data.database.OnDeleteListener
import com.example.water_drinking_whale.databinding.ActivityNoticeBinding
import java.util.Calendar

class NoticeActivity : AppCompatActivity(), OnDeleteListener {

    private lateinit var binding: ActivityNoticeBinding

    private var ampm: String? = null

    lateinit var db: AppDatabase

    var noticeList: List<Notice> = listOf<Notice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticeBinding.inflate(layoutInflater) // 바인딩 객체 획득
        setContentView(binding.root)

        db = AppDatabase.getInstance(applicationContext)!!

        // 알람 화면에 저장된 알람 목록 표시
        getAllNotice()

        // 알람 연결을 위해 db내용 list에 저장
        noticeList = db.noticeDao().getAll()

        binding.addNoticeBtn.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)

            val listener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                val notice = Notice(null, timeSet(hour), minute, AM_PM(hour))
                insertNotice(notice)
            }
            val picker = TimePickerDialog(this, listener, hour, minute, true)
            picker.show()
        }
    }

    @SuppressLint("StaticFieldLeak")
    fun insertNotice(notice: Notice) {
        val insertTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.noticeDao().insert(notice)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllNotice()
            }
        }
        insertTask.execute()
    }

    @SuppressLint("StaticFieldLeak")
    fun getAllNotice() {
        val getTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                noticeList = db.noticeDao().getAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                setRecyclerview(noticeList)
            }
        }
        getTask.execute()
    }

    @SuppressLint("StaticFieldLeak")
    fun deleteNotice(notice: Notice) {
        val deleteTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                db.noticeDao().delete(notice)
            }
            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllNotice()
            }
        }
        deleteTask.execute()
    }

    fun setRecyclerview(noticeList: List<Notice>) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.noticeRecyclerView.layoutManager = layoutManager
        binding.noticeRecyclerView.adapter = NoticeAdapter(applicationContext, noticeList, this)
    }

    override fun onDeleteListener(notice: Notice) {
        deleteNotice(notice)
    }
    // 24시간 단위 12시간 단위로 변경
    private fun timeSet(hour: Int): Int {
        var hour = hour
        if (hour > 12) {
            hour -= 12
        }
        return hour
    }

    // 오전 오후 결정
    private fun AM_PM(hour: Int): String {
        ampm = if (hour >= 12) {
            "오후"
        } else {
            "오전"
        }
        return ampm!!
    }
}

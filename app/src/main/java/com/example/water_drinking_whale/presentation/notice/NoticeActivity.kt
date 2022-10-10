package com.example.water_drinking_whale.presentation.notice

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.water_drinking_whale.R
import com.example.water_drinking_whale.data.database.AppDatabase
import com.example.water_drinking_whale.data.database.Notice
import com.example.water_drinking_whale.databinding.ActivityNoticeBinding
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import java.text.DateFormat
import java.util.*

class NoticeActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityNoticeBinding

    lateinit var db: AppDatabase

    var noticeList: ArrayList<Notice> = arrayListOf<Notice>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        db = AppDatabase.getInstance(applicationContext)!!

        // binding 초기화
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notice)

        getAllNotice()

        noticeList = db.noticeDao().getAll() as ArrayList<Notice>

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var position: Int = viewHolder.bindingAdapterPosition

                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        var id: Long? = noticeList.get(position).id
                        var hour: Int = noticeList.get(position).hour
                        var minute: Int = noticeList.get(position).minute
                        var am_pm: String? = noticeList.get(position).am_pm

                        var notice: Notice = Notice(id, hour, minute, am_pm)

                        // 삭제
                        deleteNotice(notice)
                    }
                }
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                // 스와이프 기능
                RecyclerViewSwipeDecorator.Builder(
                    c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive
                )
                    .addSwipeLeftBackgroundColor(Color.RED)
                    .addSwipeLeftActionIcon(R.drawable.ic_delete)
                    .addSwipeLeftLabel("삭제")
                    .setSwipeLeftLabelColor(Color.WHITE)
                    .create()
                    .decorate()

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }).attachToRecyclerView(binding.noticeRecyclerView)

        // 알람 설정
        binding.timeBtn.setOnClickListener {

            var timePicker = TimePickerFragment()
            // 시계 호출
            timePicker.show(supportFragmentManager, "Time Picker")
        }

//        //알람 취소
//        binding.alarmCancelBtn.setOnClickListener {
//            // 알람 취소 함수
//            cancleAlarm()
//        }
    }

    // 시간 정하면 호출되는 함수
    override fun onTimeSet(timePicker: TimePicker?, hourOfDay: Int, minute: Int) {

        val notice = Notice(null, timeSet(hourOfDay), minute, AM_PM(hourOfDay))
        insertNotice(notice)

        var notiList = db.noticeDao().getAll()

        for (i in 0 until notiList.count()) {
            var calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, notiList[i].hour)
            calendar.set(Calendar.MINUTE, notiList[i].minute)
            calendar.set(Calendar.SECOND, 0)
            startAlarm(calendar)
        }
    }

    // 알람 설정
    private fun startAlarm(calendar: Calendar) {

        // 알람매니저 선언
        var alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        var intent = Intent(this, NotificationReceiver:: class.java)

        // 데이터 담기
        var curTime = DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.time)

        intent.putExtra("time", curTime)

        var pendingIntent = PendingIntent.getBroadcast(this, getRandomRequestCode(), intent, PendingIntent.FLAG_CANCEL_CURRENT)

        // 설정 시간이 현재시간 이전이면 +1일
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1)
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    // 알람 취소
//    private fun cancleAlarm(){
//
//        //알람매니저 선언
//        var alarmManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
//
//        var intent = Intent(this, AlertReceiver:: class.java)
//
//        var pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0)
//
//        alarmManager.cancel(pendingIntent)
//        binding.timeText.text = "알람 취소"
//
//    }

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
                noticeList = db.noticeDao().getAll() as ArrayList<Notice>
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

    fun setRecyclerview(noticeList: ArrayList<Notice>) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.noticeRecyclerView.layoutManager = layoutManager
        binding.noticeRecyclerView.adapter = NoticeAdapter(applicationContext, noticeList)
    }

    private fun getRandomRequestCode() = RandomUtil.getRandomInt()

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
        var ampm = if (hour >= 12) {
            "오후"
        } else {
            "오전"
        }
        return ampm!!
    }
}

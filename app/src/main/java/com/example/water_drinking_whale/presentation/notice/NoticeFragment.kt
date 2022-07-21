package com.example.water_drinking_whale.presentation.notice

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.water_drinking_whale.MainActivity
import com.example.water_drinking_whale.data.database.AppDatabase
import com.example.water_drinking_whale.data.database.Notice
import com.example.water_drinking_whale.data.database.OnDeleteListener
import com.example.water_drinking_whale.databinding.FragmentNoticeBinding
import java.util.Calendar

class NoticeFragment : Fragment(), OnDeleteListener {

    private var _binding: FragmentNoticeBinding? = null
    private val binding get() = _binding!!

    private var ampm: String? = null

    lateinit var db: AppDatabase

    var noticeList: List<Notice> = listOf<Notice>()

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context){
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoticeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 코드 작성

        db = AppDatabase.getInstance(mainActivity)!!

        //알람 화면에 저장된 알람 목록 표시
        getAllNotice()

        //알람 연결을 위해 db내용 list에 저장
        noticeList = db.noticeDao().getAll()

        binding.addNoticeBtn.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR)
            val minute = calendar.get(Calendar.MINUTE)

            val listener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                val notice = Notice(null, timeSet(hour), minute, AM_PM(hour))
                insertNotice(notice)
            }
            val picker = TimePickerDialog(context, listener, hour, minute, true)
            picker.show()

        }
    }

    @SuppressLint("StaticFieldLeak")
    fun insertNotice(notice: Notice) {
        val insertTask = object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?){
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
    fun getAllNotice(){
        val getTask = object : AsyncTask<Unit, Unit, Unit>(){
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
    fun deleteNotice(notice: Notice){
        val deleteTask = object:AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?){
                db.noticeDao().delete(notice)
            }
            override fun onPostExecute(result: Unit?){
                super.onPostExecute(result)
                getAllNotice()
            }
        }
        deleteTask.execute()
    }

    fun setRecyclerview(noticeList : List<Notice>){
        val layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.noticeRecyclerView.layoutManager = layoutManager
        binding.noticeRecyclerView.adapter = NoticeAdapter(mainActivity, noticeList, this)
    }

    override fun onDeleteListener(notice: Notice) {
        deleteNotice(notice)
    }
    //24시간 단위 12시간 단위로 변경
    private fun timeSet(hour:Int):Int{
        var hour = hour
        if (hour> 12) {
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

    companion object {
        const val REQUEST_CODE1 = 1000
        const val REQUEST_CODE2 = 1001
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

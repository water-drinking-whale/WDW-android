package com.example.water_drinking_whale.presentation.notice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.water_drinking_whale.data.database.Notice
import com.example.water_drinking_whale.databinding.NoticeBinding

class NoticeAdapter(val context: Context, var list: ArrayList<Notice>) : RecyclerView.Adapter<NoticeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(val binding: NoticeBinding):RecyclerView.ViewHolder(binding.root){
        val am_pm = binding.ampmTv
        val hour = binding.hourTv
        val minute = binding.minuteTv
        val root = binding.noticeRoot
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val notice = list[position]
        holder.am_pm.text = notice.am_pm
        holder.hour.text = notice.hour.toString()
        holder.minute.text = notice.minute.toString()

    }



    override fun getItemCount(): Int {
        return list.size
    }
}

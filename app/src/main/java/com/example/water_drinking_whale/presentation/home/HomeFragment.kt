package com.example.water_drinking_whale.presentation.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.water_drinking_whale.R
import com.example.water_drinking_whale.databinding.DialogHomeBinding
import com.example.water_drinking_whale.databinding.FragmentHomeBinding
import com.example.water_drinking_whale.presentation.mypage.MyPageActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()
        setDB()

        binding.homeFragmentBtn.setOnClickListener {
            setWaterIntakeDialog()
        }
    }

    private fun setToolbar() {
        binding.homeFragmentToolbar.apply {
            inflateMenu(R.menu.home_fragment_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.homeSetting -> {
                        startActivity(Intent(activity, MyPageActivity::class.java))
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

        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.apply {
            setMessage(R.string.home_today_intake)
            setView(dialogBinding.root)
            setCancelable(false)
            setPositiveButton(R.string.positive_btn, null)
            setNegativeButton(R.string.negative_btn, null)
        }!!.create().apply {
            show()
            getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val todayIntake = binding.homeFragmentTodayIntakeTv
                if (intakeEt.text.toString() == "") {
                    Toast.makeText(context, "물 섭취량을 입력해 주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    val total = todayIntake.text.toString().toInt() + intakeEt.text.toString().toInt()
                    todayIntake.text = total.toString()
                    updateDB(total)
                    dismiss()
                }
            }
        }
    }

    private fun setDB() {
    }

    private fun updateDB(todayIntake: Int) {
        Log.d(
            "abc",
            SimpleDateFormat("yyyy-MM-dd", Locale("ko", "KR")).format(Date())
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

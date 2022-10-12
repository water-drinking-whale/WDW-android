package com.example.water_drinking_whale.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
    private var isFabClicked = false

    private val rotateOpenAnimation: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.fab_rotate_open_animation) }
    private val rotateCloseAnimation: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.fab_rotate_close_animation) }
    private val fromBottomAnimation: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.fab_from_bottom_animation) }
    private val toBottomAnimation: Animation by lazy { AnimationUtils.loadAnimation(this, R.anim.fab_to_bottom_animation) }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setToolbar()
        setFabClickEvent()
    }

    private fun setFabClickEvent() {
        with(binding) {
            mainFab.setOnClickListener { onMainFabClicked() }
            awardsFab.setOnClickListener {
                startActivity(Intent(this@MainActivity, AwardActivity::class.java))
            }
            noticeFab.setOnClickListener {
                startActivity(Intent(this@MainActivity, NoticeActivity::class.java))
            }
            logFab.setOnClickListener {
                startActivity(Intent(this@MainActivity, LogActivity::class.java))
            }
            intakeFab.setOnClickListener {
                setWaterIntakeDialog()
            }
        }
    }

    private fun onMainFabClicked() {
        setVisibility()
        setAnimation()

        isFabClicked = !isFabClicked
    }

    private fun setVisibility() {
        val visibility = if (!isFabClicked) View.VISIBLE else View.INVISIBLE

        with(binding) {
            awardsFab.visibility = visibility
            noticeFab.visibility = visibility
            logFab.visibility = visibility
            intakeFab.visibility = visibility
        }
    }

    private fun setAnimation() {
        val rotateAnimation = if (!isFabClicked) rotateOpenAnimation else rotateCloseAnimation
        val animation = if (!isFabClicked) fromBottomAnimation else toBottomAnimation

        with(binding) {
            mainFab.startAnimation(rotateAnimation)
            awardsFab.startAnimation(animation)
            noticeFab.startAnimation(animation)
            logFab.startAnimation(animation)
            intakeFab.startAnimation(animation)
        }
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
            setMessage(R.string.main_today_intake)
            setView(dialogBinding.root)
            setCancelable(false)
            setPositiveButton(R.string.positive_btn, null)
            setNegativeButton(R.string.negative_btn, null)
        }.create().apply {
            show()
            getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                val todayIntake = binding.mainTodayIntakeTv
                if (intakeEt.text.toString() == "") {
                    Toast.makeText(context, "물 섭취량을 입력해 주세요", Toast.LENGTH_SHORT).show()
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

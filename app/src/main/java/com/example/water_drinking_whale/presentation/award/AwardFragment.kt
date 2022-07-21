package com.example.water_drinking_whale.presentation.award

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.water_drinking_whale.databinding.FragmentAwardBinding

class AwardFragment : Fragment() {
    private var _binding: FragmentAwardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAwardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 코드 작성
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

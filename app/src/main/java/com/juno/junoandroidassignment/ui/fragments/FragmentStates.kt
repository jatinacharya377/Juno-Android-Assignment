package com.juno.junoandroidassignment.ui.fragments

import androidx.navigation.fragment.findNavController
import com.juno.junoandroidassignment.databinding.FragmentStatesBinding

class FragmentStates: FragmentBase<FragmentStatesBinding>(FragmentStatesBinding::inflate) {
    override fun setUpUi() {
        binding.btnEmptyState.setOnClickListener {
            val action = FragmentStatesDirections.actionGoToHomeScreen(0)
            findNavController().navigate(action)
        }
        binding.btnValuesState.setOnClickListener {
            val action = FragmentStatesDirections.actionGoToHomeScreen(1)
            findNavController().navigate(action)
        }
    }
}
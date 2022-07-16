package com.juno.junoandroidassignment.ui.fragments

import androidx.navigation.fragment.findNavController
import com.juno.junoandroidassignment.databinding.FragmentStatesBinding

/**
 * This screen is responsible for showing states screen which contains two button. Empty State & Values State for getting respective response from backend.
 * It extends from FragmentBase which implements the function and properties of FragmentBase.
 * @author: Jagannath Acharya
 */
class FragmentStates: FragmentBase<FragmentStatesBinding>(FragmentStatesBinding::inflate) {

    /**
     * This function is responsible for setting up our UI.
     * It is an abstract function which is implemented by extending from FragmentBase.
     */
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
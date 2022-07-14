package com.juno.junoandroidassignment.ui.fragments

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.juno.junoandroidassignment.databinding.FragmentSplashBinding

class FragmentSplash: FragmentBase<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    override fun setUpUi() {
        Handler(Looper.getMainLooper()).postDelayed({
            val action = FragmentSplashDirections.actionGoToHomeScreen()
            findNavController().navigate(action)
        }, 1000)
    }
}
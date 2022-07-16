package com.juno.junoandroidassignment.ui.fragments

import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.juno.junoandroidassignment.databinding.FragmentSplashBinding

/**
 * This screen is responsible for showing app branding to the user when app is launched.
 * It extends from FragmentBase which implements the function and properties of FragmentBase.
 * @author: Jagannath Acharya
 */
class FragmentSplash: FragmentBase<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    /**
     * This function is responsible for setting up our UI.
     * It is an abstract function which is implemented by extending from FragmentBase.
     */
    override fun setUpUi() {
        Handler(Looper.getMainLooper()).postDelayed({
            val action = FragmentSplashDirections.actionGoToHomeScreen()
            findNavController().navigate(action)
        }, 1000)
    }
}
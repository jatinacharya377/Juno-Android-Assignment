package com.juno.junoandroidassignment.ui.fragments

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.juno.junoandroidassignment.databinding.FragmentHomeBinding
import com.juno.junoandroidassignment.viewmodel.CryptoViewModel

class FragmentHome: FragmentBase<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val navArgs by navArgs<FragmentHomeArgs>()
    private val cryptoViewModel by activityViewModels<CryptoViewModel>()

    private fun setUpObserver() {
        cryptoViewModel.cryptoResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {

            }
        }
        cryptoViewModel.error.observe(viewLifecycleOwner) { error ->
            if (error.error) {

            }
        }
    }

    override fun setUpUi() {
        when  (navArgs.state) {
            0 -> cryptoViewModel.getEmptyStateCryptoResponse()
            1 -> cryptoViewModel.getValuesStateCryptoResponse()
        }
        setUpObserver()
    }
}
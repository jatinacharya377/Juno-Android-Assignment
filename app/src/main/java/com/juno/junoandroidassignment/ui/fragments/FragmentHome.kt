package com.juno.junoandroidassignment.ui.fragments

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.juno.junoandroidassignment.data.model.crypto.CryptoState
import com.juno.junoandroidassignment.databinding.FragmentHomeBinding
import com.juno.junoandroidassignment.ui.adapters.CryptoStateListAdapter
import com.juno.junoandroidassignment.viewmodel.CryptoViewModel

class FragmentHome: FragmentBase<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val adapter = CryptoStateListAdapter()
    private val cryptoViewModel by activityViewModels<CryptoViewModel>()
    private val navArgs by navArgs<FragmentHomeArgs>()


    private fun setUpObserver() {
        cryptoViewModel.cryptoState.observe(viewLifecycleOwner) { response ->
            if (response.isNullOrEmpty()) {

            } else {
                adapter.setItems(response as ArrayList<CryptoState>, navArgs.state)
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
        cryptoViewModel.state = navArgs.state
        binding.rvCryptoStateList.adapter = adapter
        setUpObserver()
    }
}
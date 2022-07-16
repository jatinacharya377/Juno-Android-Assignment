package com.juno.junoandroidassignment.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.juno.junoandroidassignment.R
import com.juno.junoandroidassignment.data.model.crypto.CryptoState
import com.juno.junoandroidassignment.databinding.DialogErrorBinding
import com.juno.junoandroidassignment.databinding.FragmentHomeBinding
import com.juno.junoandroidassignment.ui.adapters.CryptoStateListAdapter
import com.juno.junoandroidassignment.viewmodel.CryptoViewModel

class FragmentHome: FragmentBase<FragmentHomeBinding>(FragmentHomeBinding::inflate), CryptoStateListAdapter.CryptoStateListener {

    private val adapter = CryptoStateListAdapter()
    private val cryptoViewModel by activityViewModels<CryptoViewModel>()
    private val navArgs by navArgs<FragmentHomeArgs>()

    private fun setErrorAnimation(errorMessage: String, anim: Int) {
        binding.rvCryptoStateList.visibility = View.GONE
        val dialogBinding = DialogErrorBinding.inflate(LayoutInflater.from(requireContext()))
        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setView(dialogBinding.root)
            .show()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogBinding.avError.setAnimation(anim)
        dialogBinding.tvErrorMessage.text = errorMessage
        dialogBinding.btnRetry.setOnClickListener {
            dialog.dismiss()
            when  (navArgs.state) {
                0 -> cryptoViewModel.getEmptyStateCryptoResponse()
                1 -> cryptoViewModel.getValuesStateCryptoResponse()
            }
        }
    }

    private fun setUpObserver() {
        cryptoViewModel.cryptoState.observe(viewLifecycleOwner) { response ->
            if (response.isNullOrEmpty()) {
                setErrorAnimation(getString(R.string.list_is_empty_error), R.raw.anim_data_not_found)
            } else {
                binding.layoutShimmer.stopShimmer()
                binding.layoutShimmer.visibility = View.GONE
                binding.rvCryptoStateList.visibility = View.VISIBLE
                adapter.setItems(response as ArrayList<CryptoState>)
            }
        }
        cryptoViewModel.error.observe(viewLifecycleOwner) { error ->
            if (error.error) {
                when (error.errorMessage) {
                    getString(R.string.bad_request_error) -> setErrorAnimation(error.errorMessage, R.raw.anim_400)
                    getString(R.string.unauthorized_error) -> setErrorAnimation(error.errorMessage, R.raw.anim_401)
                    getString(R.string.request_not_found_error) -> setErrorAnimation(error.errorMessage, R.raw.anim_400)
                    getString(R.string.no_internet_error) -> setErrorAnimation(error.errorMessage, R.raw.anim_no_internet)
                    getString(R.string.slow_internet_error) -> setErrorAnimation(error.errorMessage, R.raw.anim_slow_internet)
                    getString(R.string.request_timeout_error) -> setErrorAnimation(error.errorMessage, R.raw.anim_request_timeout)
                    getString(R.string.something_went_wrong_error) -> setErrorAnimation(error.errorMessage, R.raw.anim_something_went_wrong)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cryptoViewModel.cryptoState.value = null
    }

    override fun setUpUi() {
        when  (navArgs.state) {
            0 -> cryptoViewModel.getEmptyStateCryptoResponse()
            1 -> cryptoViewModel.getValuesStateCryptoResponse()
        }
        cryptoViewModel.state = navArgs.state
        binding.rvCryptoStateList.adapter = adapter
        adapter.setListener(this)
        setUpObserver()
    }

    override fun onClickViewAll() {
        val action = FragmentHomeDirections.actionGoToViewAllTransactionsScreen(navArgs.state)
        findNavController().navigate(action)
    }
}
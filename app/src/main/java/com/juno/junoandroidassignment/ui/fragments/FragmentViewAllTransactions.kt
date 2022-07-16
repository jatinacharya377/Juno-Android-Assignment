package com.juno.junoandroidassignment.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.juno.junoandroidassignment.R
import com.juno.junoandroidassignment.data.model.crypto.AllTransactions
import com.juno.junoandroidassignment.databinding.DialogErrorBinding
import com.juno.junoandroidassignment.databinding.FragmentViewAllTransactionsBinding
import com.juno.junoandroidassignment.ui.adapters.RecentTransactionsListAdapter
import com.juno.junoandroidassignment.viewmodel.CryptoViewModel

/**
 * This screen is responsible for showing the list of recent transactions.
 * It extends from FragmentBase which implements the function and properties of FragmentBase.
 * @author: Jagannath Acharya
 */
class FragmentViewAllTransactions: FragmentBase<FragmentViewAllTransactionsBinding>(FragmentViewAllTransactionsBinding::inflate) {

    private val adapter = RecentTransactionsListAdapter()
    private val cryptoViewModel by activityViewModels<CryptoViewModel>()
    private val navArgs by navArgs<FragmentViewAllTransactionsArgs>()

    /**
     * This function is responsible for showing lottie animation based on the error message.
     * @param: errorMessage, anim
     */
    private fun setErrorAnimation(errorMessage: String, anim: Int) {
        binding.rvTransactionsList.visibility = View.GONE
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

    /**
     * This function is responsible for observing LiveData.
     * cryptoState - This LiveData contains the list of CryptoState
     * error - This LiveData contains the exception due to which API call has failed.
     */
    private fun setUpObserver() {
        cryptoViewModel.cryptoState.observe(viewLifecycleOwner) { response ->
            if (response.isNullOrEmpty()) {
                setErrorAnimation(getString(R.string.list_is_empty_error), R.raw.anim_data_not_found)
            } else {
                binding.layoutShimmer.stopShimmer()
                binding.layoutShimmer.visibility = View.GONE
                response.forEach { state ->
                    if (state.data.first == "RECENT_TRANSACTIONS")  {
                        adapter.setItems(state.data.second as ArrayList<AllTransactions>)
                    }
                }
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

    /**
     * This function is responsible for setting up our UI.
     * It is an abstract function which is implemented by extending from FragmentBase.
     */
    override fun setUpUi() {
        when  (navArgs.state) {
            0 -> cryptoViewModel.getEmptyStateCryptoResponse()
            1 -> cryptoViewModel.getValuesStateCryptoResponse()
        }
        cryptoViewModel.state = navArgs.state
        binding.rvTransactionsList.adapter = adapter
        setUpObserver()
    }
}
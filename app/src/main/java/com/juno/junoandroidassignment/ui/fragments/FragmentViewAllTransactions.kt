package com.juno.junoandroidassignment.ui.fragments

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.juno.junoandroidassignment.data.model.crypto.AllTransactions
import com.juno.junoandroidassignment.databinding.FragmentViewAllTransactionsBinding
import com.juno.junoandroidassignment.ui.adapters.RecentTransactionsListAdapter
import com.juno.junoandroidassignment.viewmodel.CryptoViewModel

class FragmentViewAllTransactions: FragmentBase<FragmentViewAllTransactionsBinding>(FragmentViewAllTransactionsBinding::inflate) {

    private val adapter = RecentTransactionsListAdapter()
    private val cryptoViewModel by activityViewModels<CryptoViewModel>()
    private val navArgs by navArgs<FragmentViewAllTransactionsArgs>()

    private fun setUpObserver() {
        cryptoViewModel.cryptoState.observe(viewLifecycleOwner) { response ->
            if (response.isNullOrEmpty()) {

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
                binding.layoutShimmer.stopShimmer()
                binding.layoutShimmer.visibility = View.GONE
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
        binding.rvTransactionsList.adapter = adapter
        setUpObserver()
    }
}
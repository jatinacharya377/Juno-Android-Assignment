package com.juno.junoandroidassignment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juno.junoandroidassignment.data.model.crypto.*
import com.juno.junoandroidassignment.databinding.*
import java.lang.Exception
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener

class CryptoStateListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var cryptoStateListener: CryptoStateListener
    private var cryptoStateList = ArrayList<CryptoState>()

    companion object {
        const val CRYPTO_ACC_EMPTY_STATE = 0
        const val CRYPTO_ACC_VALUES_STATE = 1
        const val CRYPTO_HOLDINGS_EMPTY_STATE = 2
        const val CRYPTO_HOLDINGS_VALUES_STATE = 3
        const val CRYPTO_RECENT_TRANSACTION = 4
        const val CRYPTO_CURRENT_PRICES = 5
    }

    interface CryptoStateListener {
        fun onClickViewAll()
    }

    fun setListener(listener: CryptoStateListener) {
        cryptoStateListener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(cryptoStateList: ArrayList<CryptoState>) {
        if (this.cryptoStateList.isNotEmpty()) {
            this.cryptoStateList.clear()
        }
        this.cryptoStateList = cryptoStateList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CRYPTO_ACC_EMPTY_STATE -> {
                val binding = LayoutCryptoAccountEmptyStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CryptoAccountEmptyStateVH(binding)
            }
            CRYPTO_ACC_VALUES_STATE -> {
                val binding = LayoutCryptoAccountValuesStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CryptoAccountValuesStateVH(binding)
            }
            CRYPTO_HOLDINGS_EMPTY_STATE -> {
                val binding = LayoutCryptoHoldingsEmptyStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CryptoHoldingsEmptyStateVH(binding)
            }
            CRYPTO_HOLDINGS_VALUES_STATE -> {
                val binding = LayoutCryptoHoldingsValuesStateBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CryptoHoldingsValuesStateVH(binding)
            }
            CRYPTO_CURRENT_PRICES -> {
                val binding = LayoutCurrentPricesBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                CurrentPricesVH(binding)
            }
            CRYPTO_RECENT_TRANSACTION -> {
                val binding = LayoutRecentTransactionsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                RecentTransactionsVH(binding)
            }
            else -> throw Exception("Invalid State!")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (cryptoStateList[position].data.first) {
            "CRYPTO_BALANCE_EMPTY_STATE" -> {
                val cryptoAccountEmptyStateVH = holder as CryptoAccountEmptyStateVH
                cryptoAccountEmptyStateVH.bind(cryptoStateList[position].data.second as CryptoBalance)
            }
            "CRYPTO_BALANCE_VALUES_STATE" -> {
                val cryptoAccountValuesStateVH = holder as CryptoAccountValuesStateVH
                cryptoAccountValuesStateVH.bind(cryptoStateList[position].data.second as CryptoBalance)
            }
            "CRYPTO_HOLDINGS_EMPTY_STATE"-> {
                val cryptoHoldingsEmptyStateVH = holder as CryptoHoldingsEmptyStateVH
                cryptoHoldingsEmptyStateVH.bind(cryptoStateList[position].data.second as ArrayList<CryptoHoldings>)
            }
            "CRYPTO_HOLDINGS_VALUES_STATE"-> {
                val cryptoHoldingsValuesStateVH = holder as CryptoHoldingsValuesStateVH
                cryptoHoldingsValuesStateVH.bind(cryptoStateList[position].data.second as ArrayList<CryptoHoldings>)
            }
            "RECENT_TRANSACTIONS" -> {
                val recentTransactionsVH = holder as RecentTransactionsVH
                recentTransactionsVH.bind(cryptoStateList[position].data.second as ArrayList<AllTransactions>)
            }
            "CRYPTO_PRICES" -> {
                val currentPricesVH = holder as CurrentPricesVH
                currentPricesVH.bind(cryptoStateList[position].data.second as ArrayList<CryptoPrices>)
            }
            else -> throw Exception("Invalid State!")
        }
    }

    override fun getItemCount() = cryptoStateList.size

    override fun getItemViewType(position: Int): Int {
        return when (cryptoStateList[position].data.first) {
            "CRYPTO_BALANCE_EMPTY_STATE" -> CRYPTO_ACC_EMPTY_STATE
            "CRYPTO_BALANCE_VALUES_STATE" -> CRYPTO_ACC_VALUES_STATE
            "CRYPTO_HOLDINGS_EMPTY_STATE"-> CRYPTO_HOLDINGS_EMPTY_STATE
            "CRYPTO_HOLDINGS_VALUES_STATE"-> CRYPTO_HOLDINGS_VALUES_STATE
            "RECENT_TRANSACTIONS" -> CRYPTO_RECENT_TRANSACTION
            "CRYPTO_PRICES" -> CRYPTO_CURRENT_PRICES
            else -> throw Exception("Invalid State!")
        }
    }

    inner class CryptoAccountEmptyStateVH(val binding: LayoutCryptoAccountEmptyStateBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoBalance: CryptoBalance) {
            binding.cryptoBalance = cryptoBalance
        }
    }

    inner class CryptoAccountValuesStateVH(val binding: LayoutCryptoAccountValuesStateBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(cryptoBalance: CryptoBalance) {
            binding.cryptoBalance = cryptoBalance
        }
    }

    inner class CryptoHoldingsEmptyStateVH(val binding: LayoutCryptoHoldingsEmptyStateBinding): RecyclerView.ViewHolder(binding.root) {
        private val adapter = CryptoHoldingsEmptyStateListAdapter()
        fun bind(list: ArrayList<CryptoHoldings>) {
            binding.rvCryptoHoldingsList.adapter = adapter
            adapter.setItems(list)
            val listener: OnItemTouchListener = object : OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    when (e.action) {
                        MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                    return false
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
            }
            binding.rvCryptoHoldingsList.addOnItemTouchListener(listener)
        }
    }

    inner class CryptoHoldingsValuesStateVH(val binding: LayoutCryptoHoldingsValuesStateBinding): RecyclerView.ViewHolder(binding.root) {
        private val adapter = CryptoHoldingsValuesStateListAdapter()
        fun bind(list: ArrayList<CryptoHoldings>) {
            binding.rvCryptoHoldingsList.adapter = adapter
            adapter.setItems(list)
            val listener: OnItemTouchListener = object : OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    when (e.action) {
                        MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                    return false
                }

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
            }
            binding.rvCryptoHoldingsList.addOnItemTouchListener(listener)
        }
    }

    inner class RecentTransactionsVH(val binding: LayoutRecentTransactionsBinding): RecyclerView.ViewHolder(binding.root) {
        private val adapter = RecentTransactionsListAdapter()
        fun bind(list: ArrayList<AllTransactions>) {
            binding.rvRecentTransactionsList.adapter = adapter
            adapter.setItems(list)
            val listener: OnItemTouchListener = object : OnItemTouchListener {
                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    when (e.action) {
                        MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                    return false
                }
                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
            }
            binding.rvRecentTransactionsList.addOnItemTouchListener(listener)
            binding.tvViewAll.setOnClickListener {
                cryptoStateListener.onClickViewAll()
            }
        }
    }

    inner class CurrentPricesVH(val binding: LayoutCurrentPricesBinding): RecyclerView.ViewHolder(binding.root) {
        private val adapter = CurrentPricesListAdapter()
        fun bind(list: ArrayList<CryptoPrices>) {
            binding.rvCurrentPricesList.adapter = adapter
            adapter.setItems(list)
        }
    }
}
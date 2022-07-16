package com.juno.junoandroidassignment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juno.junoandroidassignment.GlideApp
import com.juno.junoandroidassignment.R
import com.juno.junoandroidassignment.data.model.crypto.AllTransactions
import com.juno.junoandroidassignment.databinding.LayoutRecentTransactionsItemBinding

/**
 * This screen is responsible for populating recent transactions list.
 * @author: Jagannath Acharya
 */
class RecentTransactionsListAdapter: RecyclerView.Adapter<RecentTransactionsListAdapter.RecentTransactionsVH>() {

    private var transactionsList = ArrayList<AllTransactions>()

    /**
     * This function is responsible for setting up our transactionsList and notifying the RecyclerView about the data change.
     * @param: transactionsList
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(transactionsList: ArrayList<AllTransactions>) {
        if (this.transactionsList.isNotEmpty()) {
            this.transactionsList.clear()
        }
        this.transactionsList = transactionsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentTransactionsVH {
        val binding = LayoutRecentTransactionsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecentTransactionsVH(binding)
    }

    override fun onBindViewHolder(holder: RecentTransactionsVH, position: Int) {
        holder.bind(transactionsList[position])
    }

    override fun getItemCount() = transactionsList.size

    inner class RecentTransactionsVH(val binding: LayoutRecentTransactionsItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AllTransactions) {
            binding.transaction = item
            GlideApp.with(binding.root.context)
                .load(item.txn_logo)
                .placeholder(R.drawable.ic_app_logo_2)
                .into(binding.ivTransactionIcon)
        }
    }
}
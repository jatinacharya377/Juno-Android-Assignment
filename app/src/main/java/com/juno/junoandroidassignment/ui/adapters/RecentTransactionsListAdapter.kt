package com.juno.junoandroidassignment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.juno.junoandroidassignment.GlideApp
import com.juno.junoandroidassignment.R
import com.juno.junoandroidassignment.data.model.crypto.AllTransactions
import com.juno.junoandroidassignment.databinding.LayoutRecentTransactionsItemBinding

class RecentTransactionsListAdapter: RecyclerView.Adapter<RecentTransactionsListAdapter.RecentTransactionsVH>() {

    private var transactionsList = ArrayList<AllTransactions>()

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
package com.juno.junoandroidassignment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juno.junoandroidassignment.GlideApp
import com.juno.junoandroidassignment.R
import com.juno.junoandroidassignment.data.model.crypto.CryptoHoldings
import com.juno.junoandroidassignment.databinding.LayoutCryptoHoldingsValuesStateItemBinding

/**
 * This screen is responsible for populating values state crypto holdings list.
 * @author: Jagannath Acharya
 */
class CryptoHoldingsValuesStateListAdapter: RecyclerView.Adapter<CryptoHoldingsValuesStateListAdapter.CryptoHoldingsVH>() {
    private var holdingsList = ArrayList<CryptoHoldings>()

    /**
     * This function is responsible for setting our holdingsList and notifying the RecyclerView about the data change.
     * @param: holdingsList
     */
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(holdingsList: ArrayList<CryptoHoldings>) {
        if (this.holdingsList.isNotEmpty()) {
            this.holdingsList.clear()
        }
        this.holdingsList = holdingsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHoldingsVH {
        val binding = LayoutCryptoHoldingsValuesStateItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CryptoHoldingsVH(binding)
    }

    override fun onBindViewHolder(holder: CryptoHoldingsVH, position: Int) {
        holder.bind(holdingsList[position])
    }

    override fun getItemCount() = holdingsList.size

    inner class CryptoHoldingsVH(val binding: LayoutCryptoHoldingsValuesStateItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: CryptoHoldings) {
            binding.cryptoHolding = item
            GlideApp.with(binding.root.context)
                .load(item.logo)
                .placeholder(R.drawable.ic_app_logo_2)
                .into(binding.ivCryptoHoldingIcon)
            if (adapterPosition + 1 == holdingsList.size) {
                binding.viewDivider.visibility = View.GONE
            }
        }
    }
}
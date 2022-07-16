package com.juno.junoandroidassignment.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juno.junoandroidassignment.GlideApp
import com.juno.junoandroidassignment.R
import com.juno.junoandroidassignment.data.model.crypto.CryptoPrices
import com.juno.junoandroidassignment.databinding.LayoutCurrentPricesItemBinding

class CurrentPricesListAdapter: RecyclerView.Adapter<CurrentPricesListAdapter.CurrentPricesVH>() {

    private var priceList = ArrayList<CryptoPrices>()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(priceList: ArrayList<CryptoPrices>) {
        if (this.priceList.isNotEmpty()) {
            this.priceList.clear()
        }
        this.priceList = priceList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentPricesVH {
        val binding = LayoutCurrentPricesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CurrentPricesVH(binding)
    }

    override fun onBindViewHolder(holder: CurrentPricesVH, position: Int) {
        holder.bind(priceList[position])
    }

    override fun getItemCount() = priceList.size

    inner class CurrentPricesVH(val binding: LayoutCurrentPricesItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CryptoPrices) {
            binding.price = item
            GlideApp.with(binding.root.context)
                .load(item.logo)
                .placeholder(R.drawable.ic_app_logo_2)
                .into(binding.ivCurrentPricesIcon)
        }
    }
}
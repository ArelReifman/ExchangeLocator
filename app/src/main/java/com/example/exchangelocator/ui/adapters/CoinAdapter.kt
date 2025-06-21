package com.example.exchangelocator.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangelocator.R
import com.example.exchangelocator.databinding.RowCoinBinding
import com.example.exchangelocator.models.CoinDetail
import java.text.NumberFormat
import java.util.Currency

class CoinAdapter : RecyclerView.Adapter<CoinAdapter.ViewHolder>() {

    private val coins: MutableList<CoinDetail> = mutableListOf()

    interface OnCoinItemClickListener {
        fun onCoinClick(coin: CoinDetail, position: Int)
    }

    private var listener: OnCoinItemClickListener? = null

    fun setOnCoinItemClickListener(listener: OnCoinItemClickListener) {
        this.listener = listener
    }

    fun updateList(newCoins: List<CoinDetail>) {
        coins.clear()
        coins.addAll(newCoins)
        notifyDataSetChanged()
    }

    private fun getFlagResource(currencyCode: String): Int {
        return when (currencyCode.uppercase()) {
            "USD" -> R.drawable.usa_flag
            "EUR" -> R.drawable.europe_flag
            "ILS" -> R.drawable.israel_flag
            "JPY" -> R.drawable.japan_flag
            "GBP" -> R.drawable.uk_flag
            else -> R.drawable.bg_globe_centered
        }
    }

    private fun formatAmount(amount: Double, currencyCode: String): String {
        val format = NumberFormat.getCurrencyInstance()
        format.currency = Currency.getInstance(currencyCode)
        if (currencyCode == "JPY") {
            format.maximumFractionDigits = 0
            format.minimumFractionDigits = 0
        } else {
            format.maximumFractionDigits = 2
            format.minimumFractionDigits = 2
        }

        return format.format(amount)
    }

    inner class ViewHolder(private val binding: RowCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onCoinClick(coins[position], position)
                }
            }
        }

        fun bind(coin: CoinDetail) {
            binding.typeOfCoinTitle.text = coin.toCurrency
            binding.amountAfterConvert.text = formatAmount(coin.resultAmount, coin.toCurrency)
            binding.coinImage.setImageResource(getFlagResource(coin.toCurrency))
            binding.root.alpha = 1f
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val coin = coins[position]
        holder.bind(coin)
    }

    override fun getItemCount(): Int = coins.size

    fun getCoinAtPosition(position: Int): CoinDetail {
        return coins[position]
    }
}
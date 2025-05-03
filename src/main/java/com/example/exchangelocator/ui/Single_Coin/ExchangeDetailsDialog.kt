package com.example.exchangelocator.ui.Single_Coin

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import com.example.exchangelocator.Data.models.ExchangePoint
import com.example.exchangelocator.databinding.DialogLayoutBinding

class ExchangeDetailsDialog(
    context: Context,
    private val exchangePoint: ExchangePoint
) : Dialog(context) {

    private lateinit var binding: DialogLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        binding = DialogLayoutBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)


        binding.tvExchangeName.text = exchangePoint.name
        binding.ivExchangeImage.setImageResource(exchangePoint.imageResId)
        binding.tvCountry.text = exchangePoint.country
        binding.tvCity.text = exchangePoint.city
        binding.tvStreet.text = exchangePoint.street
        binding.tvOpeningHours.text = exchangePoint.openingHours


        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}
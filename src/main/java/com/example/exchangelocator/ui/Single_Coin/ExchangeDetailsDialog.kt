package com.example.exchangelocator.ui.Single_Coin

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
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


        val width = (context.resources.displayMetrics.widthPixels * 0.90).toInt()
        window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)


        window?.setBackgroundDrawableResource(android.R.color.transparent)


        setCancelable(false)


        binding.tvExchangeName.text = "EXCHANGE"


        binding.ivExchangeImage.setImageResource(exchangePoint.imageResId)
        binding.ivExchangeImage.scaleType = ImageView.ScaleType.CENTER_CROP


        binding.valueCountry.text = exchangePoint.country
        binding.valueCity.text = exchangePoint.city
        binding.valueAddress.text = exchangePoint.street
        binding.valueHours.text = exchangePoint.openingHours


        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }
}
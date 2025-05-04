package com.example.exchangelocator.ui.Single_Coin

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import com.example.exchangelocator.Data.models.ExchangePoint
import com.example.exchangelocator.R
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
        binding.tvExchangeName.text = context.getString(R.string.exchange)
        binding.ivExchangeImage.setImageResource(exchangePoint.imageResId)
        binding.ivExchangeImage.scaleType = ImageView.ScaleType.CENTER_CROP

        // תרגום מדינות וערים ישירות בדיאלוג
        binding.valueCountry.text = translateCountry(exchangePoint.country)
        binding.valueCity.text = translateCity(exchangePoint.city)
        binding.valueAddress.text = translateAddress(exchangePoint.street)
        binding.valueHours.text = exchangePoint.openingHours

        binding.btnClose.setOnClickListener {
            dismiss()
        }
    }

    private fun translateCountry(country: String): String {
        return when (country) {
            "USA" -> "ארה״ב"
            "UK" -> "בריטניה"
            "Germany" -> "גרמניה"
            "France" -> "צרפת"
            "Japan" -> "יפן"
            "Israel" -> "ישראל"
            else -> country
        }
    }

    private fun translateCity(city: String): String {
        return when (city) {
            "New York" -> "ניו יורק"
            "London" -> "לונדון"
            "Frankfurt" -> "פרנקפורט"
            "Paris" -> "פריז"
            "Tokyo" -> "טוקיו"
            "Tel Aviv" -> "תל אביב"
            else -> city
        }
    }

    private fun translateAddress(address: String): String {
        return when (address) {
            "JFK International Airport,Terminal 8" -> "JFK שדה תעופה בינלאומי, טרמינל 8"
            "JFK International Airport,Terminal 4" -> "JFK שדה תעופה בינלאומי, טרמינל 4"
            "Heathrow Airport,Terminal 5" -> "שדה תעופה היתרו, טרמינל 5"
            "Heathrow Airport London Underground Station" -> "תחנת רכבת תחתית בשדה תעופה היתרו לונדון"
            "Frankfurt Airport,Terminal 1" -> "שדה תעופה פרנקפורט, טרמינל 1"
            "Charles de Gaulle Airport,Terminal 2E" -> "שדה תעופה שארל דה גול, טרמינל 2E"
            "Narita International Airport,Terminal 1" -> "שדה תעופה בינלאומי נריטה, טרמינל 1"
            "Haneda Airport,Terminal 3" -> "שדה תעופה האנדה, טרמינל 3"
            "Ben Gurion Airport,Terminal 3" -> "שדה תעופה בן גוריון, טרמינל 3"
            else -> address
        }
    }
}
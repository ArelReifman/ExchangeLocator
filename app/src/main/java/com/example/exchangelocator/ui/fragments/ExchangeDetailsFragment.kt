package com.example.exchangelocator.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.exchangelocator.databinding.DialogLayoutBinding
import com.example.exchangelocator.models.ExchangePoint
import com.example.exchangelocator.utils.TranslationHelper

class ExchangeDetailsFragment : Fragment() {
    private var _binding: DialogLayoutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvExchangeName.text =
            requireContext().getString(com.example.exchangelocator.R.string.exchange)
        val exchangePoint = getExchangePoint() ?: return
        binding.ivExchangeImage.setImageResource(exchangePoint.imageResId)
        binding.ivExchangeImage.scaleType = ImageView.ScaleType.CENTER_CROP

        binding.valueCountry.text = getCountryString(exchangePoint.country)
        binding.valueCity.text = getCityString(exchangePoint.city)
        binding.valueAddress.text = getAddressString(exchangePoint.street)
        binding.valueHours.text = exchangePoint.openingHours
    }

    private fun getExchangePoint(): ExchangePoint? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable("exchangePoint", ExchangePoint::class.java)
        } else {
            @Suppress("DEPRECATION")
            arguments?.getParcelable("exchangePoint")
        }
    }

    private fun getCountryString(country: String): String {
        return when (country) {
            "USA" -> getString(com.example.exchangelocator.R.string.country_usa)
            "UK" -> getString(com.example.exchangelocator.R.string.country_uk)
            "Germany" -> getString(com.example.exchangelocator.R.string.country_germany)
            "France" -> getString(com.example.exchangelocator.R.string.country_france)
            "Japan" -> getString(com.example.exchangelocator.R.string.country_japan)
            "Israel" -> getString(com.example.exchangelocator.R.string.country_israel)
            else -> country
        }
    }

    private fun getCityString(city: String): String {
        return when (city) {
            "New York" -> getString(com.example.exchangelocator.R.string.city_new_york)
            "London" -> getString(com.example.exchangelocator.R.string.city_london)
            "Frankfurt" -> getString(com.example.exchangelocator.R.string.city_frankfurt)
            "Paris" -> getString(com.example.exchangelocator.R.string.city_paris)
            "Tokyo" -> getString(com.example.exchangelocator.R.string.city_tokyo)
            "Tel Aviv" -> getString(com.example.exchangelocator.R.string.city_tel_aviv)
            else -> city
        }
    }

    private fun getAddressString(address: String): String {
        return when (address) {
            "JFK International Airport,Terminal 8" -> getString(com.example.exchangelocator.R.string.address_jfk_terminal_8)
            "JFK International Airport,Terminal 4" -> getString(com.example.exchangelocator.R.string.address_jfk_terminal_4)
            "Heathrow Airport,Terminal 5" -> getString(com.example.exchangelocator.R.string.address_heathrow_terminal_5)
            "Heathrow Airport London Underground Station" -> getString(com.example.exchangelocator.R.string.address_heathrow_underground)
            "Frankfurt Airport,Terminal 1" -> getString(com.example.exchangelocator.R.string.address_frankfurt_terminal_1)
            "Charles de Gaulle Airport,Terminal 2E" -> getString(com.example.exchangelocator.R.string.address_charles_de_gaulle)
            "Narita International Airport,Terminal 1" -> getString(com.example.exchangelocator.R.string.address_narita_terminal_1)
            "Haneda Airport,Terminal 3" -> getString(com.example.exchangelocator.R.string.address_haneda_terminal_3)
            "Ben Gurion Airport,Terminal 3" -> getString(com.example.exchangelocator.R.string.address_ben_gurion_terminal_3)
            else -> address
        }
    }
}
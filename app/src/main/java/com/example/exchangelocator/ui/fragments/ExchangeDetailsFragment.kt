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
        binding.valueCountry.text =
            TranslationHelper.translateCountry(requireContext(), exchangePoint.country)
        binding.valueCity.text =
            TranslationHelper.translateCity(requireContext(), exchangePoint.city)
        binding.valueAddress.text =
            TranslationHelper.translateAddress(requireContext(), exchangePoint.street)
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
}
package com.example.exchangelocator.ui.fragments

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.example.exchangelocator.R
import com.example.exchangelocator.databinding.FragmentExchangeDetailsBinding // Changed this
import com.example.exchangelocator.models.ExchangePoint
// Removed TranslationHelper as it's unused after string externalization
// import com.example.exchangelocator.utils.TranslationHelper
// import com.example.exchangelocator.utils.getParcelableCompat // This will be removed by SafeArgs
import com.example.exchangelocator.models.CoinDetail
import androidx.navigation.fragment.navArgs

class ExchangeDetailsFragment : Fragment() {
    private var _binding: FragmentExchangeDetailsBinding? = null // Changed this
    private val binding get() = _binding!!

    private val args: ExchangeDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentExchangeDetailsBinding.inflate(inflater, container, false) // Changed this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        // binding.tvExchangeName.text no longer needs initial R.string.exchange, will be set by logic below
        val exchangePoint = args.exchangePoint // Using SafeArgs
        binding.ivExchangeImage.setImageResource(exchangePoint.imageResId)
        binding.ivExchangeImage.scaleType = ImageView.ScaleType.CENTER_CROP

        // Resolve string keys from ExchangePoint to actual strings
        binding.valueCountry.text = getStringResourceByName(exchangePoint.country)
        binding.valueCity.text = getStringResourceByName(exchangePoint.city)
        binding.valueAddress.text = getStringResourceByName(exchangePoint.street)
        binding.valueHours.text = getStringResourceByName(exchangePoint.openingHours)

        // Update the name of the exchange point as well
        // binding.tvExchangeName.text = getStringResourceByName(exchangePoint.name) // Initial name setting

        val coin = args.coin // Using SafeArgs
        if (coin != null) {
            // If coin data is available, format the title to show currency pair
            binding.tvExchangeName.text = getString(R.string.exchange_currency_format, coin.fromCurrency, coin.toCurrency)
        } else {
            // If no coin data, set tvExchangeName to the exchange point's name.
            binding.tvExchangeName.text = getStringResourceByName(exchangePoint.name)
        }
    }

    // Removed getExchangePoint() and getCoin() as SafeArgs handles argument retrieval

    private fun getStringResourceByName(stringName: String): String {
        val resId = resources.getIdentifier(stringName, "string", requireContext().packageName)
        return if (resId != 0) {
            getString(resId)
        } else {
            stringName // Fallback to the key itself if not found, though this shouldn't happen
        }
    }

    private fun setupToolbar() {
        binding.toolbarExchangeDetails.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}
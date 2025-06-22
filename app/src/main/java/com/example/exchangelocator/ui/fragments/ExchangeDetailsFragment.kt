package com.example.exchangelocator.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.exchangelocator.R
import com.example.exchangelocator.databinding.DialogLayoutBinding
import com.example.exchangelocator.models.ExchangePoint
import com.example.exchangelocator.models.CoinDetail
import com.example.exchangelocator.utils.TranslationHelper
import java.text.NumberFormat
import java.util.Currency

class ExchangeDetailsFragment : Fragment() {
    private var _binding: DialogLayoutBinding? = null
    private val binding get() = _binding!!
    private val args: ExchangeDetailsFragmentArgs by navArgs()

    private val pickImage = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            binding.ivCustomPhoto.setImageURI(it)
            binding.layoutCustomImage.visibility = View.VISIBLE
            Toast.makeText(context, getString(R.string.photo_selected), Toast.LENGTH_SHORT).show()
        }
    }

    private val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCameraDirectly()
        } else {
            Toast.makeText(context, "צריך הרשאה למצלמה", Toast.LENGTH_SHORT).show()
        }
    }

    private val takePicture = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { bitmap: Bitmap? ->
        bitmap?.let {
            binding.ivCustomPhoto.setImageBitmap(it)
            binding.layoutCustomImage.visibility = View.VISIBLE
            Toast.makeText(context, getString(R.string.photo_selected), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exchangePoint = args.exchangePoint
        val coin = args.coin

        setupViews(exchangePoint, coin)
        setupButtons()
    }

    private fun setupViews(exchangePoint: ExchangePoint, coin: CoinDetail?) {
        binding.ivExchangeImage.setImageResource(exchangePoint.imageResId)
        binding.ivExchangeImage.scaleType = ImageView.ScaleType.CENTER_CROP

        binding.valueCountry.text = TranslationHelper.getStringByKey(requireContext(), exchangePoint.country)
        binding.valueCity.text = TranslationHelper.getStringByKey(requireContext(), exchangePoint.city)
        binding.valueAddress.text = TranslationHelper.getStringByKey(requireContext(), exchangePoint.street)
        binding.valueHours.text = TranslationHelper.getStringByKey(requireContext(), exchangePoint.openingHours)

        if (coin != null) {
            val fromAmount = formatCurrency(coin.originalAmount, coin.fromCurrency)
            val toAmount = formatCurrency(coin.resultAmount, coin.toCurrency)
            binding.tvExchangeName.text = "$fromAmount → $toAmount\n${coin.fromCurrency} → ${coin.toCurrency}"
        } else {
            binding.tvExchangeName.text = TranslationHelper.getStringByKey(requireContext(), exchangePoint.name)
        }
    }

    private fun setupButtons() {
        binding.btnTakePhoto.setOnClickListener {
            checkCameraPermission()
        }

        binding.btnChoosePhoto.setOnClickListener {
            pickImage.launch("image/*")
        }

        binding.btnViewGallery.setOnClickListener {
            openExchangeGallery()
        }
    }

    private fun checkCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                openCameraDirectly()
            }
            else -> {
                requestPermission.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun openCameraDirectly() {
        takePicture.launch(null)
    }

    private fun openExchangeGallery() {
        Toast.makeText(context, getString(R.string.gallery_coming_soon), Toast.LENGTH_SHORT).show()
    }

    private fun formatCurrency(amount: Double, currencyCode: String): String {
        val format = NumberFormat.getCurrencyInstance()
        format.currency = Currency.getInstance(currencyCode)
        return format.format(amount)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
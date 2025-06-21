package com.example.exchangelocator.ui.fragments

import android.R
import android.os.Bundle
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.TextViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.exchangelocator.databinding.AddCoinBinding
import com.example.exchangelocator.models.CoinDetail
import com.example.exchangelocator.viewmodels.CoinViewModel
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip

class AddCoinFragment : Fragment() {
    private var _binding: AddCoinBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = AddCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        TextViewCompat.setAutoSizeTextTypeWithDefaults(
            binding.outputConvert,
            TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
        )

        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
            binding.outputConvert,
            12,
            18,
            2,
            TypedValue.COMPLEX_UNIT_SP
        )

        binding.outputConvert.maxLines = 1
        binding.outputConvert.ellipsize = TextUtils.TruncateAt.END


        TextViewCompat.setAutoSizeTextTypeWithDefaults(
            binding.userInputAmount,
            TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
        )

        TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(
            binding.userInputAmount,
            12,
            18,
            2,
            TypedValue.COMPLEX_UNIT_SP
        )

        binding.userInputAmount.maxLines = 1
        binding.userInputAmount.ellipsize = TextUtils.TruncateAt.END

        viewModel = ViewModelProvider(requireActivity())[CoinViewModel::class.java]

        val coins = listOf("USD", "EUR", "ILS", "JPY", "GBP")

        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, coins)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerUserCoin.setAdapter(adapter)
        binding.spinnerDestinationCoin.setAdapter(adapter)

        val rates = mapOf(
            "USD" to 0.28,
            "EUR" to 0.24,
            "ILS" to 1.0,
            "JPY" to 39.26,
            "GBP" to 0.21
        )

        binding.btnConvert.setOnClickListener {
            val fromCurrency = binding.spinnerUserCoin.selectedItem.toString()
            val toCurrency = binding.spinnerDestinationCoin.selectedItem.toString()
            val inputAmount = binding.userInputAmount.text.toString().toDoubleOrNull()

            if (inputAmount != null && rates.containsKey(fromCurrency) && rates.containsKey(
                    toCurrency
                )
            ) {
                val usdAmount = inputAmount / rates[fromCurrency]!!
                val converted = usdAmount * rates[toCurrency]!!

                binding.outputConvert.text = String.format("%.2f", converted)
            } else {
                binding.outputConvert.text =
                    getString(com.example.exchangelocator.R.string.invalid_input)
            }
        }

        binding.btnSave.setOnClickListener {
            val fromCurrency = binding.spinnerUserCoin.selectedItem.toString()
            val toCurrency = binding.spinnerDestinationCoin.selectedItem.toString()
            val inputAmount = binding.userInputAmount.text.toString().toDoubleOrNull()
            val outputText = binding.outputConvert.text.toString().toDoubleOrNull()

            if (inputAmount != null && outputText != null) {
                val newCoin = CoinDetail(fromCurrency, toCurrency, inputAmount, outputText)
                viewModel.addCoin(newCoin)
                Toast.makeText(
                    requireContext(),
                    getString(com.example.exchangelocator.R.string.coin_saved_successfully),
                    Toast.LENGTH_SHORT
                ).show()
                findNavController().navigate(com.example.exchangelocator.R.id.action_addCoinFragment_to_coinRecyclerViewFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    getString(com.example.exchangelocator.R.string.please_convert_before_saving),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        binding.swapIcon.setOnClickListener {
            val fromPosition = binding.spinnerUserCoin.selectedItemPosition
            val toPosition = binding.spinnerDestinationCoin.selectedItemPosition
            binding.spinnerUserCoin.setSelection(toPosition)
            binding.spinnerDestinationCoin.setSelection(fromPosition)
        }

        binding.helpIcon.setOnClickListener {
            showTooltip(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showTooltip(anchorView: View) {
        val tooltipBuilder = SimpleTooltip.Builder(requireContext())
        tooltipBuilder.anchorView(anchorView)
        tooltipBuilder.text(getString(com.example.exchangelocator.R.string.tooltip_help_message))
        tooltipBuilder.gravity(Gravity.START)
        val textColor =
            ContextCompat.getColor(requireContext(), com.example.exchangelocator.R.color.white)
        val bgColor = ContextCompat.getColor(
            requireContext(),
            com.example.exchangelocator.R.color.converter_card_outer
        )
        tooltipBuilder.textColor(textColor)
        tooltipBuilder.backgroundColor(bgColor)
        tooltipBuilder.arrowColor(bgColor)
        tooltipBuilder.animated(true)
        tooltipBuilder.transparentOverlay(false)
        val tooltip = tooltipBuilder.build()
        tooltip.show()
    }
}
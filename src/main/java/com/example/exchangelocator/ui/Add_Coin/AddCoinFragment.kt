package com.example.exchangelocator.ui.Add_Coin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.exchangelocator.Data.models.CoinDetail
import com.example.exchangelocator.R
import com.example.exchangelocator.ui.CoinViewModel
import com.example.exchangelocator.databinding.AddCoinBinding

class AddCoinFragment : Fragment() {
    private val coinViewModel: CoinViewModel by activityViewModels()
    private var _binding: AddCoinBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AddCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(requireActivity())[CoinViewModel::class.java]

        val coins = listOf("USD", "EUR", "ILS", "JPY", "GBP")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, coins)
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

            if (inputAmount != null && rates.containsKey(fromCurrency) && rates.containsKey(toCurrency)) {
                val usdAmount = inputAmount / rates[fromCurrency]!!
                val converted = usdAmount * rates[toCurrency]!!

                binding.outputConvert.text = String.format("%.2f", converted)
            } else {
                binding.outputConvert.text = "Invalid input"
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

                Toast.makeText(requireContext(), "Coin saved successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addCoinFragment_to_coinRecyclerViewFragment)
            } else {
                Toast.makeText(requireContext(), "Please convert before saving", Toast.LENGTH_SHORT).show()
            }
        }

        binding.swapIcon.setOnClickListener {
            val fromPosition = binding.spinnerUserCoin.selectedItemPosition
            val toPosition = binding.spinnerDestinationCoin.selectedItemPosition
            binding.spinnerUserCoin.setSelection(toPosition)
            binding.spinnerDestinationCoin.setSelection(fromPosition)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
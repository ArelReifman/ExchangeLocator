package com.example.exchangelocator.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.exchangelocator.R
import com.example.exchangelocator.databinding.FragmentCoinDetailViewBinding
import com.example.exchangelocator.models.CoinDetail
import com.example.exchangelocator.models.ExchangePoint
import com.example.exchangelocator.viewmodels.CoinViewModel

class CoinDetailViewFragment : Fragment() {

    private var _binding: FragmentCoinDetailViewBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CoinViewModel by activityViewModels()
    private val args: CoinDetailViewFragmentArgs by navArgs()

    private lateinit var currentCoinDetail: CoinDetail

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        currentCoinDetail = args.coinDetail

        populateDetails()
        setupToolbar()

        binding.btnEditConversion.setOnClickListener {
            val action = CoinDetailViewFragmentDirections.actionCoinDetailViewFragmentToCoinDetailEditFragment(currentCoinDetail)
            findNavController().navigate(action)
        }

        binding.btnViewExchangeLocations.setOnClickListener {
            // Reusing the logic from CoinRecyclerViewFragment for showing exchange points
            showExchangePointsList(currentCoinDetail.toCurrency, currentCoinDetail)
        }
    }

    private fun setupToolbar() {
        binding.toolbarCoinDetail.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun populateDetails() {
        binding.tvValueFromCurrency.text = currentCoinDetail.fromCurrency
        binding.tvValueToCurrency.text = currentCoinDetail.toCurrency
        binding.tvValueOriginalAmount.text = String.format("%.2f", currentCoinDetail.originalAmount)
        binding.tvValueConvertedAmount.text = String.format("%.2f", currentCoinDetail.resultAmount)
        // Potentially update toolbar title
        binding.toolbarCoinDetail.title = "${currentCoinDetail.fromCurrency} to ${currentCoinDetail.toCurrency}"
    }

    // Copied and adapted from CoinRecyclerViewFragment - consider refactoring to a shared utility or ViewModel function
    private fun showExchangePointsList(currencyCode: String, coin: CoinDetail) {
        val exchangePoints = viewModel.getExchangePointsByCurrency(currencyCode)
        if (exchangePoints.isNotEmpty()) {
            val exchangePointNames = exchangePoints.map { ep ->
                // Resolve exchange point name from string resource key
                val nameResId = resources.getIdentifier(ep.name, "string", requireContext().packageName)
                if (nameResId != 0) getString(nameResId) else ep.name
            }.toTypedArray()


            AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
                .setTitle(getString(R.string.exchange_points_for, currencyCode))
                .setItems(exchangePointNames) { _, position ->
                    showExchangePointDetails(exchangePoints[position], coin)
                }
                .setNegativeButton(getString(R.string.close), null)
                .create()
                .show()
        } else {
            Toast.makeText(context, getString(R.string.no_exchange_points, currencyCode), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showExchangePointDetails(exchangePoint: ExchangePoint, coin: CoinDetail) {
        // Navigate to ExchangeDetailsFragment using Safe Args from this fragment
        val action = CoinDetailViewFragmentDirections.actionCoinDetailViewFragmentToExchangeDetailsFragment(exchangePoint, coin)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

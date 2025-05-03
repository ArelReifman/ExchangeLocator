package com.example.exchangelocator.ui.All_Coins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangelocator.Data.models.CoinDetail
import com.example.exchangelocator.Data.models.ExchangePoint
import com.example.exchangelocator.Data.models.ExchangePointProvider
import com.example.exchangelocator.R
import com.example.exchangelocator.ui.CoinViewModel
import com.example.exchangelocator.databinding.RecyclerViewCoinBinding
import com.example.exchangelocator.ui.Single_Coin.ExchangeDetailsDialog

class CoinRecyclerViewFragment : Fragment(), CoinAdapter.OnCoinItemClickListener {

    private val viewModel: CoinViewModel by activityViewModels()
    private lateinit var coinAdapter: CoinAdapter
    private var _binding: RecyclerViewCoinBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = RecyclerViewCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coinAdapter = CoinAdapter()
        coinAdapter.setOnCoinItemClickListener(this)
        binding.recyclerViewLayout.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewLayout.adapter = coinAdapter

        viewModel.coin?.observe(viewLifecycleOwner) { coinsList ->
            if (coinsList != null) {
                coinAdapter.updateList(coinsList)
            }
        }

        val swipeCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (position != RecyclerView.NO_POSITION && position < coinAdapter.itemCount) {
                    val coin = coinAdapter.getCoinAtPosition(position)

                    val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
                        .setTitle("Delete Coin")
                        .setMessage("Are you sure you want to delete ${coin.toCurrency}?")
                        .setPositiveButton("Yes") { _, _ ->
                            viewModel.deleteCoin(coin)
                            Toast.makeText(requireContext(), "Deleted!", Toast.LENGTH_SHORT).show()
                        }
                        .setNegativeButton("No") { dialogInterface, _ ->
                            coinAdapter.notifyItemChanged(position)
                            Toast.makeText(requireContext(), "Your action was canceled", Toast.LENGTH_SHORT).show()
                            dialogInterface.dismiss()
                        }
                        .setCancelable(false)

                    val alertDialog = dialogBuilder.create()
                    alertDialog.show()
                }
            }
        }

        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding.recyclerViewLayout)

        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_coinRecyclerViewFragment_to_addCoinFragment)
        }
    }

    override fun onCoinClick(coin: CoinDetail, position: Int) {
        val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .setTitle("Exchange Location")
            .setMessage("Do you want to open ${coin.toCurrency} exchange places in your area?")
            .setPositiveButton("Open") { _, _ ->
                showExchangePointsList(coin.toCurrency)
            }
            .setNegativeButton("Close") { dialogInterface, _ ->
                coinAdapter.notifyItemChanged(position)
                Toast.makeText(requireContext(), "Your action was canceled", Toast.LENGTH_SHORT).show()
                dialogInterface.dismiss()
            }
            .setCancelable(false)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun showExchangePointsList(currencyCode: String) {
        val exchangePoints = ExchangePointProvider.getExchangePointsByCurrency(currencyCode)

        if (exchangePoints.isNotEmpty()) {
            val exchangeNames = exchangePoints.map { it.name }.toTypedArray()

            val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
                .setTitle("Exchange Points for $currencyCode")
                .setItems(exchangeNames) { _, position ->
                    showExchangePointDetails(exchangePoints[position])
                }
                .setNegativeButton("Close", null)

            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        } else {
            Toast.makeText(context, "No exchange points available for this currency", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showExchangePointDetails(exchangePoint: ExchangePoint) {
        val dialog = ExchangeDetailsDialog(requireContext(), exchangePoint)
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
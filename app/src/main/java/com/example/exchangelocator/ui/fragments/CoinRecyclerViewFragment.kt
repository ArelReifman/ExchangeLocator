package com.example.exchangelocator.ui.fragments

import android.graphics.Paint
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
import com.example.exchangelocator.R
import com.example.exchangelocator.databinding.RecyclerViewCoinBinding
import com.example.exchangelocator.models.CoinDetail
import com.example.exchangelocator.models.ExchangePoint
import com.example.exchangelocator.viewmodels.CoinViewModel
import com.example.exchangelocator.ui.adapters.CoinAdapter

class CoinRecyclerViewFragment : Fragment(), CoinAdapter.OnCoinItemClickListener {

    private val viewModel: CoinViewModel by activityViewModels()
    private val coinAdapter: CoinAdapter = CoinAdapter()
    private var _binding: RecyclerViewCoinBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        _binding = RecyclerViewCoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinAdapter.setOnCoinItemClickListener(this)
        binding.recyclerViewLayout.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewLayout.adapter = coinAdapter
        binding.aboutUsLink.paintFlags = binding.aboutUsLink.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.aboutUsLink.setOnClickListener {
            findNavController().navigate(R.id.action_coinRecyclerViewFragment_to_aboutUsFragment)
        }
        viewModel.coin.observe(viewLifecycleOwner) { coinsList ->
            coinAdapter.updateList(coinsList)
        }

        val swipeCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ) = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (position != RecyclerView.NO_POSITION && position < coinAdapter.itemCount) {
                    val coin = coinAdapter.getCoinAtPosition(position)

                    val dialogBuilder =
                        AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
                            .setTitle(getString(R.string.delete_coin))
                            .setMessage(getString(R.string.are_you_sure_delete, coin.toCurrency))
                            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                                viewModel.deleteCoin(coin)
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.deleted),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .setNegativeButton(getString(R.string.no)) { dialogInterface, _ ->
                                coinAdapter.notifyItemChanged(position)
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.action_canceled),
                                    Toast.LENGTH_SHORT
                                ).show()
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
            .setTitle(getString(R.string.exchange_location))
            .setMessage(getString(R.string.do_you_want_to_open, coin.toCurrency))
            .setPositiveButton(getString(R.string.open)) { _, _ ->
                showExchangePointsList(coin.toCurrency, coin)
            }
            .setNegativeButton(getString(R.string.close)) { dialogInterface, _ ->
                coinAdapter.notifyItemChanged(position)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.action_canceled),
                    Toast.LENGTH_SHORT
                ).show()
                dialogInterface.dismiss()
            }
            .setCancelable(false)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun showExchangePointsList(currencyCode: String, coin: CoinDetail) {
        val exchangePoints = viewModel.getExchangePointsByCurrency(currencyCode)
        if (exchangePoints.isNotEmpty()) {
            val exchangeNames = exchangePoints.map { it.name }.toTypedArray()

            val dialogBuilder = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
                .setTitle(getString(R.string.exchange_points_for, currencyCode))
                .setItems(exchangeNames) { _, position ->
                    showExchangePointDetails(
                        exchangePoints[position], coin
                    )
                }
                .setNegativeButton(getString(R.string.close), null)

            val alertDialog = dialogBuilder.create()
            alertDialog.show()
        } else {
            Toast.makeText(context, getString(R.string.no_exchange_points), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun showExchangePointDetails(exchangePoint: ExchangePoint, coin: CoinDetail) {
        findNavController().navigate(
            R.id.action_coinRecyclerViewFragment_to_exchangeDetailsFragment,
            Bundle().apply {
                putParcelable("exchangePoint", exchangePoint)
                putParcelable("coin", coin)
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
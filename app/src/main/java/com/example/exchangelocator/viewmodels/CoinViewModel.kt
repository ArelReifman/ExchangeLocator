package com.example.exchangelocator.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.exchangelocator.models.CoinDetail
import com.example.exchangelocator.models.ExchangePoint
import com.example.exchangelocator.repository.CoinRepository
import kotlinx.coroutines.launch

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CoinRepository(application)
    val coin: LiveData<List<CoinDetail>> = repository.getAllCoinsLiveData()

    fun addCoin(coin: CoinDetail) {
        viewModelScope.launch { repository.addCoin(coin) }
    }

    fun updateCoin(coin: CoinDetail) {
        viewModelScope.launch { repository.updateCoin(coin) }
    }

    fun deleteCoin(coin: CoinDetail) {
        viewModelScope.launch { repository.deleteCoin(coin) }
    }

    fun getExchangePointsByCurrency(
        currencyCode: String,
    ): List<ExchangePoint> {
        return repository.getExchangePointsByCurrency(
            currencyCode = currencyCode,
        )
    }
}
package com.example.exchangelocator.ui
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.exchangelocator.Data.models.CoinDetail
import com.example.exchangelocator.Data.models.repository.CoinRepository

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = CoinRepository(application)
    val coin: LiveData<List<CoinDetail>>? = repository.getAllCoins()

    fun addCoin(coin: CoinDetail) {
        repository.addCoin(coin)
    }

    fun deleteCoin(coin: CoinDetail) {
        repository.deleteCoin(coin)
    }
}//test1
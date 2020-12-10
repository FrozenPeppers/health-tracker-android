package nl.jorisebbelaar.tracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.jorisebbelaar.tracker.model.ProductLog
import nl.jorisebbelaar.tracker.repository.ProductLogRepository

class ProductLogViewModel(application: Application) : AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val productLogRepository = ProductLogRepository(application.applicationContext)

    val productlog: LiveData<List<ProductLog>> = productLogRepository.getAllProducts()

    fun insertProductLog(productLog: ProductLog) {
        ioScope.launch { productLogRepository.insertProductLog(productLog)
        }
    }
}
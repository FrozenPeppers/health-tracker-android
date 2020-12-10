package nl.jorisebbelaar.tracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.jorisebbelaar.tracker.model.Product
import nl.jorisebbelaar.tracker.repository.ProductRepository
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class ProductViewModel(application: Application): AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val productRepository = ProductRepository(application.applicationContext)

    private var start = LocalDate.now().atStartOfDay()
    private var end = LocalDate.now().atStartOfDay()

    val products: LiveData<List<Product>> = productRepository.getAllProducts(convertToDateViaInstant(start), convertToDateViaInstant(end.plusHours(23)))

    fun insertProduct(product: Product) {
        ioScope.launch {
            productRepository.insertProduct(product)

        }
    }

    fun deleteProduct(product: Product){
        ioScope.launch {
            productRepository.deleteProduct(product)
        }
    }

    private fun convertToDateViaInstant(dateToConvert: LocalDateTime): Date {
        return Date
            .from(
                dateToConvert.atZone(ZoneId.systemDefault())
                    .toInstant()
            )
    }

}
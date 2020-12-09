package nl.jorisebbelaar.tracker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.jorisebbelaar.tracker.model.Product
import nl.jorisebbelaar.tracker.repository.ProductRepository

class ProductViewModel(application: Application): AndroidViewModel(application) {
    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val productRepository = ProductRepository(application.applicationContext)

    val products: LiveData<List<Product>> = productRepository.getAllProducts()

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

}
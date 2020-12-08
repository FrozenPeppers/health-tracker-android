package nl.jorisebbelaar.tracker.repository

import android.content.Context
import androidx.lifecycle.LiveData
import nl.jorisebbelaar.tracker.dao.ProductDao
import nl.jorisebbelaar.tracker.database.ProductRoomDatabase
import nl.jorisebbelaar.tracker.model.Product

class ProductRepository(context: Context) {

    private var productDao: ProductDao = ProductRoomDatabase.getDatabase(context)!!.productDao()

    fun getAllProducts(): LiveData<List<Product>> {
        return productDao.getAllProduct()
    }

    fun getProductById(id: Long): Product? {
        return productDao.getProductById(id)
    }

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }


    suspend fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }

    suspend fun updateProduct(product: Product) {
        productDao.updateProduct(product)

    }
}
package nl.jorisebbelaar.tracker.repository

import android.content.Context
import androidx.lifecycle.LiveData
import nl.jorisebbelaar.tracker.dao.ProductLogDao
import nl.jorisebbelaar.tracker.database.ProductRoomDatabase
import nl.jorisebbelaar.tracker.model.ProductLog

class ProductLogRepository(context: Context) {
    private var productLogDao: ProductLogDao =
        ProductRoomDatabase.getDatabase(context)!!.productLogDao()

    fun getAllProducts(): LiveData<List<ProductLog>> {
        return productLogDao.getAllProduct()
    }

    suspend fun insertProductLog(productLog: ProductLog) {
        productLogDao.insertProductLog(productLog)
    }
}
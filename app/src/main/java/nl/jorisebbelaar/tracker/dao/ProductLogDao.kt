package nl.jorisebbelaar.tracker.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import nl.jorisebbelaar.tracker.model.ProductLog

@Dao
interface ProductLogDao {

    @Query("SELECT * FROM product_log")
    fun getAllProduct(): LiveData<List<ProductLog>>

    @Insert
    suspend fun insertProductLog(productLog: ProductLog)
}
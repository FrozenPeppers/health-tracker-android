package nl.jorisebbelaar.tracker.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import nl.jorisebbelaar.tracker.model.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProduct(): LiveData<List<Product>>

    @Query("SELECT * FROM product WHERE id = :id")
    fun getProductById(id: Long): Product

    @Insert
    suspend fun insertProduct(product: Product)

    @Delete
    suspend fun deleteProduct(product: Product)

    @Update
    suspend fun updateProduct(product: Product)
}
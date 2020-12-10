package nl.jorisebbelaar.tracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nl.jorisebbelaar.tracker.converter.Converters
import nl.jorisebbelaar.tracker.dao.ProductDao
import nl.jorisebbelaar.tracker.model.Product

@TypeConverters(Converters::class)
@Database(entities = [Product::class], version = 28, exportSchema = false)
abstract class ProductRoomDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {
        private const val DATABASE_NAME = "PRODUCT_DATABASE"

        @Volatile
        private var productRoomDatabaseInstance: ProductRoomDatabase? = null

        fun getDatabase(context: Context): ProductRoomDatabase? {
            if (productRoomDatabaseInstance == null) {
                synchronized(ProductRoomDatabase::class.java) {
                    if (productRoomDatabaseInstance == null) {
                        productRoomDatabaseInstance = databaseBuilder(
                            context.applicationContext,
                            ProductRoomDatabase::class.java, DATABASE_NAME
                        ).fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return productRoomDatabaseInstance
        }
    }
}
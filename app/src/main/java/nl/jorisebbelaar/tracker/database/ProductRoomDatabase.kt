package nl.jorisebbelaar.tracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nl.jorisebbelaar.tracker.Converters
import nl.jorisebbelaar.tracker.dao.ProductDao
import nl.jorisebbelaar.tracker.dao.ProductLogDao
import nl.jorisebbelaar.tracker.model.Product
import nl.jorisebbelaar.tracker.model.ProductLog

@TypeConverters(Converters::class)
@Database(entities = [Product::class, ProductLog::class], version = 27, exportSchema = false)
abstract class ProductRoomDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun productLogDao(): ProductLogDao

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
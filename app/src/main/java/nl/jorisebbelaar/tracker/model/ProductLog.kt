package nl.jorisebbelaar.tracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "product_log")
class ProductLog(
    var productId: Long,

    var amount: Float,

    var grams: Float,

    var insert_date: Date = Date(),

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Serializable
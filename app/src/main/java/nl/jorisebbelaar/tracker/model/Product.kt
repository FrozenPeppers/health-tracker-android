package nl.jorisebbelaar.tracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "product")
class Product(
    var productId: String,

    var name: String,

    var kcal: Double,

    var carbs: Double,

    var fat: Double,

    var protein: Double,

    var fiber: Double,

    var image_url: String,

    var insert_date: Date = Date(),

    var ammount: Float? = 1.toFloat(),

    var grams: Float? = 100.toFloat(),

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Serializable
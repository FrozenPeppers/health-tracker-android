package nl.jorisebbelaar.tracker.model

import java.io.Serializable


class Product(
    var productId: String,
    var name: String,
    var kcal: Double,
    var carbs: Double,
    var fat: Double,
    var protein: Double,
    var fiber: Double,
    var image_url: String
) : Serializable {}
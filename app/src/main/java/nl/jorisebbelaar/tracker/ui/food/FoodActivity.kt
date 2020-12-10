package nl.jorisebbelaar.tracker.ui.food

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.activity_food.*
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.model.Product
import nl.jorisebbelaar.tracker.ui.product.ProductsOverviewActivity
import nl.jorisebbelaar.tracker.viewmodel.ProductViewModel
import org.eazegraph.lib.models.PieModel
import java.util.*

class FoodActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel
    private var products = arrayListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        initViews()
        initViewModel()

    }

    private fun initViews() {
        btnProductsOverview.setOnClickListener {
            startActivity(Intent(this, ProductsOverviewActivity::class.java))
        }

    }

    private fun initViewModel() {
        viewModel = ProductViewModel(application)

        viewModel.products.observe(this, Observer { product ->
            products.clear()
            products.addAll(product)
            setMacroNutrients()
        })
    }

    private fun setMacroNutrients() {
        var kcal = 0.0
        var carbs = 0.0
        var protein = 0.0
        var fat = 0.0

        for (product in products) {
            kcal += product.kcal
            carbs += product.carbs
            protein += product.protein
            fat += product.fat
        }

        tvPiechartCarbs.text = kcal.toString()
        tvPiechartProtein.text = protein.toString()
        tvPiechartFat.text = fat.toString()

        updatePiechart()
    }

    private fun updatePiechart() {
        piechart.addPieSlice(
            PieModel(
                "Carbohydrates",
                tvPiechartCarbs.text.toString().toFloat(),
                Color.parseColor("#FFA726")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "Fat",
                tvPiechartFat.text.toString().toFloat(),
                Color.parseColor("#66BB6A")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "Protein",
                tvPiechartProtein.text.toString().toFloat(),
                Color.parseColor("#29B6F6")
            )
        )

        piechart.startAnimation()
    }
}
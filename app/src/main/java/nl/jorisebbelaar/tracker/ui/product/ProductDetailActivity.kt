package nl.jorisebbelaar.tracker.ui.product

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.model.Product
import nl.jorisebbelaar.tracker.viewmodel.ProductViewModel
import org.eazegraph.lib.models.PieModel
import java.util.*

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ProductViewModel(application)

        val product = this.intent.getSerializableExtra("product") as Product

        tvProductLabel.text = product.name
        tvPiechartKcal.text = product.kcal.toString()
        tvPiechartCarbs.text = product.carbs.toString()
        tvPiechartFat.text = product.fat.toString()
        tvPiechartProtein.text = product.protein.toString()
        tvPiechartFiber.text = product.fiber.toString()
        Picasso.get().load(product.image_url).into(ivProductDetail)

        updatePieChart()

        editTextField1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) else calcRatio(
                product,
                editTextField1.text.toString().toFloat(),
                editTextField2.text.toString().toInt()
            )
        }

        buttonAddProduct.setOnClickListener {
            calcRatio(
                product,
                editTextField1.text.toString().toFloat(),
                editTextField2.text.toString().toInt()
            )
            product.insert_date = Date()

            addProductToLog(product)
        }
    }

    private fun addProductToLog(product: Product) {
        product.ammount = editTextField1.text.toString().toFloat()
        product.grams = editTextField2.text.toString().toFloat()
        viewModel.insertProduct(product)
    }

    private fun calcRatio(product: Product?, amount: Float, grams: Int) {
        var ratio = (grams / 100.0) * amount

        tvProductLabel.text = product?.name
        tvPiechartKcal.text = (product?.kcal?.times(ratio)).toString()
        tvPiechartCarbs.text = (product?.carbs?.times(ratio)).toString()
        tvPiechartFat.text = (product?.fat?.times(ratio)).toString()
        tvPiechartProtein.text = (product?.protein?.times(ratio)).toString()
        tvPiechartFiber.text = (product?.fiber?.times(ratio)).toString()
        Picasso.get().load(product?.image_url).into(ivProductDetail)

        updatePieChart()
    }

    private fun updatePieChart() {
        piechart.clearChart()

        piechart.addPieSlice(
            PieModel(
                "Carbohydrates",
                tvPiechartCarbs.text.toString().toFloat(),
                Color.parseColor("#FFA726")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "Protein",
                tvPiechartProtein.text.toString().toFloat(),
                Color.parseColor("#66BB6A")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "Fat",
                tvPiechartFat.text.toString().toFloat(),
                Color.parseColor("#29B6F6")
            )
        )
        piechart.startAnimation()
    }
}
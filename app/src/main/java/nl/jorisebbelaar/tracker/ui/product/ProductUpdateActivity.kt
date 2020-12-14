package nl.jorisebbelaar.tracker.ui.product

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.activity_products_overview.*
import kotlinx.android.synthetic.main.item_product.*
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.model.Product
import nl.jorisebbelaar.tracker.viewmodel.ProductViewModel
import org.eazegraph.lib.models.PieModel
import java.util.*

class ProductUpdateActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        viewModel = ProductViewModel(application)

        val product = this.intent.getSerializableExtra("product") as Product



        tvProductLabel.text = product.name
        tvPiechartKcal.text = product.kcal.toString()
        tvPiechartCarbs.text = product.carbs.toString()
        tvPiechartFat.text = product.fat.toString()
        tvPiechartProtein.text = product.protein.toString()
        tvPiechartFiber.text = product.fiber.toString()

        buttonAddProduct.text = "update"
        editTextField1.setText(product.ammount.toString())
        editTextField2.setText(product.grams.toString())

        Picasso.get().load(product.image_url).into(ivProductDetail)

        updatePieChart()

        editTextField1.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) else calcRatio(
                product,
                editTextField1.text.toString().toFloat(),
                editTextField2.text.toString().toInt()
            )
        }

        calcRatio(product, product.ammount!!, product.grams!!.toInt())

        buttonAddProduct.setOnClickListener {
            calcRatio(
                product,
                editTextField1.text.toString().toFloat(),
                editTextField2.text.toString().toFloat().toInt()
            )
            product.insert_date = Date()

            updateProduct(product)
        }
    }

    private fun showToast() {
        Toast.makeText(this, "succesfully updated product", Toast.LENGTH_LONG).show()
    }

    private fun updateProduct(product: Product) {
        product.ammount = editTextField1.text.toString().toFloat()
        product.grams = editTextField2.text.toString().toFloat()

        viewModel.updateProduct(product)

        showToast()
        startActivity(Intent(this, ProductsOverviewActivity::class.java))

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
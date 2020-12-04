package nl.jorisebbelaar.tracker.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_food.*
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.activity_product_detail.piechart
import kotlinx.android.synthetic.main.activity_product_detail.tvPiechartCarbs
import kotlinx.android.synthetic.main.activity_product_detail.tvPiechartFat
import kotlinx.android.synthetic.main.activity_product_detail.tvPiechartProtein
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.model.Product
import org.eazegraph.lib.models.PieModel

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val product = this.intent.getSerializableExtra("product") as? Product
        println(product?.name)

        tvProductLabel.text = product?.name
        tvPiechartKcal.text = product?.kcal.toString()
        tvPiechartCarbs.text = product?.carbs.toString()
        tvPiechartFat.text = product?.fat.toString()
        tvPiechartProtein.text = product?.protein.toString()
        tvPiechartFiber.text = product?.fiber.toString()
        Picasso.get().load(product?.image_url).into(ivProductDetail)

        updatePieChart()

        editTextField1.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) else calcRatio(product, editTextField1.text.toString().toFloat(), 100)
        }
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
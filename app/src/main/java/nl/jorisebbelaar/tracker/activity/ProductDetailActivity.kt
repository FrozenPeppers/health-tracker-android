package nl.jorisebbelaar.tracker.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_product_detail.*
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.model.Product

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
    }
}
package nl.jorisebbelaar.tracker.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_food.*
import nl.jorisebbelaar.tracker.R
import org.eazegraph.lib.models.PieModel

class FoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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
                Color.parseColor("#29B6F6")
            )
        )
        piechart.addPieSlice(
            PieModel(
                "Fat",
                tvPiechartFat.text.toString().toFloat(),
                Color.parseColor("#66BB6A")
            )
        )

        piechart.startAnimation()

        initViews()
    }

    private fun initViews(){
        btnProductsOverview.setOnClickListener{
            startActivity(Intent(this, ProductsOverviewActivity::class.java))
        }

    }
}
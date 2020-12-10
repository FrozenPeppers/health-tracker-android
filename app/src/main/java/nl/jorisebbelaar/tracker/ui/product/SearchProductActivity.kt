package nl.jorisebbelaar.tracker.ui.product

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_search_product.*
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.adapter.ProductAdapter
import nl.jorisebbelaar.tracker.model.Product
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import kotlin.math.roundToInt

class SearchProductActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private val productList = arrayListOf<Product>()
    private val productAdapter = ProductAdapter(productList) { product ->
        var intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra("product", product as Serializable)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_product)

        rvSearchProduct.layoutManager =
            LinearLayoutManager(this@SearchProductActivity, RecyclerView.VERTICAL, false)
        rvSearchProduct.adapter = productAdapter
        rvSearchProduct.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )

        val search = findViewById<SearchView>(R.id.svProduct)
        search.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        getFood(query.toString())
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun getFood(query: String) {

        productList.removeAll(productList)
        productAdapter.notifyDataSetChanged()

        val queue = Volley.newRequestQueue(this)
        val url =
            "https://api.edamam.com/api/food-database/v2/parser?app_id=e0f22721&app_key=e7e3cf8237bfa97302f9c274c8662141&ingr=$query"

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val jsonObject = JSONObject(response.toString())
                val parsedArr = jsonObject["hints"]

                // loop
                for (i in 0..7) {
                    val food = JSONArray(parsedArr.toString())[i]
                    val parsedFood = JSONObject(food.toString())
                    val foodObj = parsedFood.getJSONObject("food")
                    val productId = foodObj["foodId"].toString()
                    val label = foodObj["label"].toString()
                    val nutrients = foodObj.getJSONObject("nutrients")
                    var kcal = 0.0
                    var protein = 0.0
                    var fat = 0.0
                    var carbs = 0.0
                    var fiber = 0.0
                    var image_url = "xxx.com"
                    if (nutrients.has("ENERC_KCAL")) {
                        kcal = nutrients["ENERC_KCAL"].toString().toDouble()
                    }
                    if (nutrients.has("PROCNT")) {
                        protein = nutrients["PROCNT"].toString().toDouble()
                    }
                    if (nutrients.has("FAT")) {
                        fat = nutrients["FAT"].toString().toDouble()
                    }
                    if (nutrients.has("CHOCDF")) {
                        carbs = nutrients["CHOCDF"].toString().toDouble()
                    }
                    if (nutrients.has("FIBTG")) {
                        fiber = nutrients["FIBTG"].toString().toDouble()
                    }
                    if (foodObj.has("image")) {
                        image_url = foodObj["image"].toString()
                    }

                    val product = Product(
                        productId = productId,
                        name = label,
                        kcal = ((kcal * 1000) / 1000).roundToInt().toDouble(),
                        protein = ((protein * 1000) / 1000).roundToInt().toDouble(),
                        carbs = ((carbs * 1000) / 1000).roundToInt().toDouble(),
                        fat = ((fat * 1000) / 1000).roundToInt().toDouble(),
                        fiber = ((fiber * 1000) / 1000).roundToInt().toDouble(),
                        image_url = image_url
                    )
                    productList.add(product)
                    productAdapter.notifyDataSetChanged()
                }

            },
            {})
        queue.add(stringRequest)
    }
}
package nl.jorisebbelaar.tracker.ui.product

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_products_overview.*
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.adapter.ProductLogAdapter
import nl.jorisebbelaar.tracker.model.Product

class ProductsOverviewActivity : AppCompatActivity() {

    private val productList = arrayListOf<Product>()
    private val productLogAdapter = ProductLogAdapter(productList)
    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_overview)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        rvProducts.layoutManager =
            LinearLayoutManager(this@ProductsOverviewActivity, RecyclerView.VERTICAL, false)
        rvProducts.adapter = productLogAdapter
        rvProducts.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )
        btnProductAdd.setOnClickListener {
            startActivity(Intent(this, SearchProductActivity::class.java))
        }
    }

    private fun initViewModel() {
        viewModel = ProductViewModel(application)

        viewModel.products.observe(this, Observer { products ->
            productList.clear()
            productList.addAll(products)
            productLogAdapter.notifyDataSetChanged()
            println("=================================================")
        })
    }
}
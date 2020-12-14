package nl.jorisebbelaar.tracker.ui.product

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_products_overview.*
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.adapter.ProductLogAdapter
import nl.jorisebbelaar.tracker.model.Product
import nl.jorisebbelaar.tracker.ui.food.FoodActivity
import nl.jorisebbelaar.tracker.viewmodel.ProductViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class ProductsOverviewActivity : AppCompatActivity() {

    private val productList = arrayListOf<Product>()
    private val productLogAdapter = ProductLogAdapter(productList)
    private lateinit var viewModel: ProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_overview)

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

        btnProductsOverview.setOnClickListener{
            startActivity(Intent(this, FoodActivity::class.java))
        }

        createItemTouchHelper().attachToRecyclerView(rvProducts)
    }

    private fun initViewModel() {
        viewModel = ProductViewModel(application)

        viewModel.products.observe(this, Observer { products ->
            productList.clear()
            productList.addAll(products)
            productLogAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val product = productList[viewHolder.adapterPosition]
                viewModel.deleteProduct(product)
                showUndoSnackbar(product)
            }
        }

        return ItemTouchHelper(callback)
    }

    private fun showUndoSnackbar(product: Product) {
        val snackbar = Snackbar.make(
            findViewById(R.id.activityProductsOverview),
            "removed product", Snackbar.LENGTH_SHORT
        )
        snackbar.setAction("undo") {
            viewModel.insertProduct(product)

        }
        snackbar.show()
    }
}
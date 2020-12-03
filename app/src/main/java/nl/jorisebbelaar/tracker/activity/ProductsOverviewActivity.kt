package nl.jorisebbelaar.tracker.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_products_overview.*
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.adapter.ProductLogAdapter
import nl.jorisebbelaar.tracker.model.Product
import nl.jorisebbelaar.tracker.model.ProductLog
import java.util.*

class ProductsOverviewActivity : AppCompatActivity() {

    private val productList = arrayListOf<ProductLog>()
    private val productAdapter = ProductLogAdapter(productList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_overview)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    private fun initViews() {
        rvProducts.layoutManager =
            LinearLayoutManager(this@ProductsOverviewActivity, RecyclerView.VERTICAL, false)
        rvProducts.adapter = productAdapter
        rvProducts.addItemDecoration(
            DividerItemDecoration(
                applicationContext,
                DividerItemDecoration.VERTICAL
            )
        )
        createItemTouchHelper().attachToRecyclerView(rvProducts)

        btnProductAdd.setOnClickListener {
            startActivity(Intent(this, SearchProductActivity::class.java))
        }
    }


    private fun addProduct() {
        val product = Product("Apple", "productId",90.0, 30.0, 1.0, 1.0, 40.0, "")
        productList.add(ProductLog(product, 1, Date()))
        productAdapter.notifyDataSetChanged()
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
                val product = productList[(viewHolder.adapterPosition)]
                productList.removeAt(viewHolder.adapterPosition)
                productAdapter.notifyDataSetChanged()
                showRemovedProductSnackbar(product)
            }
        }

        return ItemTouchHelper(callback)
    }

    private fun showRemovedProductSnackbar(productLog: ProductLog) {
        val snackbar = Snackbar.make(
            findViewById(R.id.activityProductsOverview),
            "Removed " + productLog.amount + "x " + productLog.product.name + " from log",
            Snackbar.LENGTH_SHORT
        )
        snackbar.show()
    }
}
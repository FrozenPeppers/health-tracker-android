package nl.jorisebbelaar.tracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_products_overview.*
import nl.jorisebbelaar.tracker.adapter.ProductAdapter
import nl.jorisebbelaar.tracker.model.Product

class ProductsOverviewActivity : AppCompatActivity() {

    private val productList = arrayListOf<Product>()
    private val productAdapter = ProductAdapter(productList)
    private lateinit var viewModel: ProductsOverviewActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_overview)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initViews()
    }

    private fun initViews(){
        rvProducts.layoutManager = LinearLayoutManager(this@ProductsOverviewActivity, RecyclerView.VERTICAL, false)
        rvProducts.adapter = productAdapter
        rvProducts.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvProducts)
        testbutton.setOnClickListener(){
            addProduct()
        }
    }

    private fun addProduct(){
        val product = Product("Apple", 10,100, 1, 40, 2, 2, "xxx.com")
        productList.add(product)
        productAdapter.notifyDataSetChanged()
        println(product)
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

    private fun showRemovedProductSnackbar(product: Product){
        val snackbar = Snackbar.make(
            findViewById(R.id.activityProductsOverview),
            "Removed " + product.amount + "x " + product.name + " from log", Snackbar.LENGTH_SHORT
        )
        snackbar.show()
    }
}
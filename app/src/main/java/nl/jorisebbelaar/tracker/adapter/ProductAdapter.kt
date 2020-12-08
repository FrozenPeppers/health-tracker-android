package nl.jorisebbelaar.tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.model.Product

class ProductAdapter(
    private val products: List<Product>,
    private val listener: (Product) -> Unit
) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_products,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
        holder.itemView.setOnClickListener { listener(product) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val label: TextView = itemView.findViewById(R.id.stat_1)
        private val kcal: TextView = itemView.findViewById(R.id.stat_2)
        private val btn: Button = itemView.findViewById(R.id.stat_3)

        fun bind(products: Product) {
            label.text = products.name
            kcal.text = products.kcal.toString() + " Kcal"
        }
    }

}
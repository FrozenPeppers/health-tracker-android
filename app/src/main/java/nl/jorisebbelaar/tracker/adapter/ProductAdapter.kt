package nl.jorisebbelaar.tracker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_product.view.*
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.model.Product
import org.w3c.dom.Text

class ProductAdapter(private val products: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_product,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvAmount: TextView = itemView.findViewById(R.id.tvAmmount)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvKcal: TextView = itemView.findViewById(R.id.tvKcal)

        fun bind(product: Product) {
            tvAmount.text = product.amount.toString() + " x"
            tvName.text = product.name
            tvKcal.text = product.kcal.toString() + " kcal"
        }
    }
}
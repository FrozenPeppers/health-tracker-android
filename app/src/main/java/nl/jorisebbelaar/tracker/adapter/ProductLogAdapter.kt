package nl.jorisebbelaar.tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.model.Product

class ProductLogAdapter(
    private val productLogs: List<Product>,
    private val listener: (Product) -> Unit
) :
    RecyclerView.Adapter<ProductLogAdapter.ViewHolder>() {
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
        return productLogs.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productLogs[position]
        holder.bind(product)
        holder.itemView.setOnClickListener { listener(product) }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvAmount: TextView = itemView.findViewById(R.id.tvAmmount)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvKcal: TextView = itemView.findViewById(R.id.tvKcal)

        fun bind(productLog: Product) {
            var ratio = (productLog.grams!! / 100.0) * productLog.ammount!!
            tvAmount.text = productLog.ammount.toString() + " x"
            tvName.text = productLog.name
            tvKcal.text = productLog.kcal.toString() + " kcal"
        }
    }
}
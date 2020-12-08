package nl.jorisebbelaar.tracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import nl.jorisebbelaar.tracker.R
import nl.jorisebbelaar.tracker.model.Product

class ProductLogAdapter(private val productLogs: List<Product>) :
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
        holder.bind(productLogs[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvAmount: TextView = itemView.findViewById(R.id.tvAmmount)
        private val tvName: TextView = itemView.findViewById(R.id.tvName)
        private val tvKcal: TextView = itemView.findViewById(R.id.tvKcal)

        fun bind(productLog: Product) {
            tvAmount.text = productLog.ammount.toString()
            tvName.text = productLog.name
            tvKcal.text = productLog.kcal.toString()

        }
    }
}
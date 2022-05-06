package com.example.tooskawood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tooskawood.database.Ingredients

class IngredientListAdapter(var dataset: List<Ingredients?>) : RecyclerView.Adapter<IngredientListAdapter.ListViewHolder>() {

    /*lateinit var itemlistener:onItemClickListener
    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener:onItemClickListener){
        itemlistener=listener
    }*/
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientName=itemView.findViewById<TextView>(R.id.tv_ingredient)
        val ingredientCode=itemView.findViewById<TextView>(R.id.tv_ingredient_code)
        val ingredientAmount=itemView.findViewById<TextView>(R.id.tv_ingredient_amount)
        val ingredientDescr=itemView.findViewById<TextView>(R.id.tv_ingredient_desc)
       /* init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item_view, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.ingredientName.text=dataset[position]?.ingredientName
        holder.ingredientCode.text=dataset[position]?.code
        holder.ingredientAmount.text=dataset[position]?.amount
        holder.ingredientDescr.text=dataset[position]?.description
    }

    override fun getItemCount(): Int = dataset.size
}

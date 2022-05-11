package com.example.tooskawood.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tooskawood.R
import com.example.tooskawood.database.Ingredients

class IngredientListAdapter(var dataset: MutableList<Ingredients>) :
    RecyclerView.Adapter<IngredientListAdapter.ListViewHolder>() {

    /*lateinit var buttonEditlistener: onButtonEditClickListener


    interface onButtonEditClickListener {
        fun onBtnEditClick(position: Int)
    }

    fun setOnItemClickListener(editListener: onButtonEditClickListener) {
        buttonEditlistener = editListener
    }*/

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ingredientName = itemView.findViewById<TextView>(R.id.tv_ingredient)
        val ingredientCode = itemView.findViewById<TextView>(R.id.tv_ingredient_code)
        val ingredientAmount = itemView.findViewById<TextView>(R.id.tv_ingredient_amount)
        val ingredientDescr = itemView.findViewById<TextView>(R.id.tv_ingredient_desc)
        val ingredientConvertedAmnt = itemView.findViewById<TextView>(R.id.tv_ingredient_converted_amnt)
        val editBtn = itemView.findViewById<ImageButton>(R.id.ibtn_edit)

        /*init {
            editBtn.setOnClickListener {
            editListener.onBtnEditClick(adapterPosition)
            }
        }*/

        fun bind(position: Int) {
            ingredientName.text = dataset[position]?.ingredientName
            ingredientCode.text = dataset[position]?.code
            ingredientAmount.text = dataset[position]?.amount
            ingredientDescr.text = dataset[position]?.description
            ingredientConvertedAmnt.text = dataset[position]?.convertedAmount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_item_view, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = dataset.size
}

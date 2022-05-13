package com.example.tooskawood.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tooskawood.R
import com.example.tooskawood.database.Ingredients

typealias ItemClickHandler = (Ingredients) -> Unit

class IngredientListAdapter(var onItemClick: ItemClickHandler) :
    androidx.recyclerview.widget.ListAdapter<Ingredients, IngredientListAdapter.ListViewHolder>(
        ListDiffCallBack
    ) {


    inner class ListViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val ingredientName = itemView.findViewById<TextView>(R.id.tv_ingredient)
        val ingredientCode = itemView.findViewById<TextView>(R.id.tv_ingredient_code)
        val ingredientAmount = itemView.findViewById<TextView>(R.id.tv_ingredient_amount)
        val ingredientDescr = itemView.findViewById<TextView>(R.id.tv_ingredient_desc)
        val ingredientConvertedAmnt =
            itemView.findViewById<TextView>(R.id.tv_ingredient_converted_amnt)
        val editBtn = itemView.findViewById<ImageButton>(R.id.ibtn_edit)


        fun bind(ingredient: Ingredients, onItemClick: ItemClickHandler){
            ingredientName.text = ingredient.ingredientName
            ingredientCode.text = ingredient.code
            ingredientAmount.text = ingredient.amount
            ingredientDescr.text = ingredient.description
            ingredientConvertedAmnt.text = ingredient.convertedAmount

            editBtn.setOnClickListener {
                onItemClick.invoke(ingredient)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_item_view, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(getItem(position),onItemClick)
    }


    object ListDiffCallBack : DiffUtil.ItemCallback<Ingredients>() {
        override fun areItemsTheSame(oldItem: Ingredients, newItem: Ingredients): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Ingredients, newItem: Ingredients): Boolean {
            return oldItem == newItem
        }

    }
}

package com.example.tooskawood

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tooskawood.database.Glaze

class ListAdapter(var dataset: List<Glaze?>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

   /* lateinit var itemlistener:onItemClickListener
    interface onItemClickListener{

        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener:onItemClickListener){
        itemlistener=listener
    }
*/
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val glazeName=itemView.findViewById<TextView>(R.id.textView_name)
        val glazeCode=itemView.findViewById<TextView>(R.id.textView_code)
       /* init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
       holder.glazeName.text=dataset[position]?.name
        holder.glazeCode.text=dataset[position]?.id.toString()
    }

    override fun getItemCount(): Int = dataset.size
}

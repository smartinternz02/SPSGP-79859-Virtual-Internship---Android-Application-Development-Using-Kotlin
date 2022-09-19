package com.example.grocerylist.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.R
import com.example.grocerylist.database.entity.GroceryItems

class ItemAdapter(private val c:Context, var itemList:List<GroceryItems>, private val viewModel:GroceryViewModel):RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    inner class ItemViewHolder(v: View): RecyclerView.ViewHolder(v)
    {
        var name: TextView = v.findViewById(R.id.txtItemName)
        var quantity:TextView = v.findViewById(R.id.txtItemQuantity)
        var price:TextView = v.findViewById(R.id.txtItemPrice)
        var tcost:TextView = v.findViewById(R.id.txtItemTotalCost)
        private var delete:ImageButton = v.findViewById(R.id.ibDelete)

        init {
            delete.setOnClickListener { deletes(adapterPosition) }
             }
    }

        private fun deletes(Position:Int) {
           viewModel.delete(itemList[Position])
            Toast.makeText(c,"Deleted this Information", Toast.LENGTH_SHORT).show()

        }
//
//

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v  = inflater.inflate(R.layout.list_item,parent,false)
        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val newList = itemList[position]
        holder.name.text = newList.itemName
        holder.quantity.text = newList.itemQuantity.toString()
        holder.price.text = newList.itemCost.toString()
        holder.tcost.text = newList.totalCost.toString()
    }

    override fun getItemCount(): Int {
        return  itemList.size
    }
}

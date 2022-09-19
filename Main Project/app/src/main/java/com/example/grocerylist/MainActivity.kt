package com.example.grocerylist

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocerylist.database.GroceryDatabase
import com.example.grocerylist.database.GroceryRepository
import com.example.grocerylist.database.entity.GroceryItems
import com.example.grocerylist.view.GroceryViewModel
import com.example.grocerylist.view.GroceryViewModelFactory
import com.example.grocerylist.view.ItemAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var addsBtn: FloatingActionButton

    private lateinit var rvList: RecyclerView
    private lateinit var viewModel: GroceryViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.ic_groceries_svgrepo_com__1_)
        supportActionBar?.setDisplayUseLogoEnabled(true)
        supportActionBar?.hideOffset
        addsBtn = findViewById(R.id.addingBtn)
      rvList = findViewById(R.id.mRecycler)



        val groceryRepository = GroceryRepository(GroceryDatabase(this))

        val factory =
            GroceryViewModelFactory(groceryRepository)

//         Initialised View Model
        viewModel = ViewModelProvider(this,factory)[GroceryViewModel::class.java]

        val groceryAdapter =
            ItemAdapter(this,listOf(), viewModel)


        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = groceryAdapter

//        To display all items in recycler view
        viewModel.allGroceryItems().observe(this) {
            groceryAdapter.itemList = it
            groceryAdapter.notifyDataSetChanged()


        }




        addsBtn.setOnClickListener { addInfo() }
    }

    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item,null)
        /**set view*/
        val itemName = v.findViewById<EditText>(R.id.itemName)
        val itemCost = v.findViewById<EditText>(R.id.itemCost)
        val itemQuantity = v.findViewById<EditText>(R.id.itemQuantity)

        AlertDialog.Builder(this)

        .setView(v)
        .setCancelable(false)
        .setPositiveButton("Add"){

                    dialog,_->
                val names = itemName.text.toString()
                if (TextUtils.isEmpty(names)){
                    Toast.makeText(this,"Enter a valid name",Toast.LENGTH_SHORT).show()
                    dialog.dismiss()}
                else {
                    val cost = itemCost.text.toString()
                    val costs = if (cost != "") (cost.toInt()) else 0
                    val quantity = itemQuantity.text.toString()
                    val quantities = if (quantity != "") (quantity.toInt()) else 0
                    val item = GroceryItems(names, quantities, costs, (costs * quantities))
                    viewModel.insert(item)
                    rvList.adapter?.notifyDataSetChanged()

                    Toast.makeText(this, "Adding New Item Success", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
        }
        .setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()

        }
        .create()
        .show()


    }



}

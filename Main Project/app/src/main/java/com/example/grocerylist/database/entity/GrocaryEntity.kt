package com.example.grocerylist.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grocery_items")

data class GroceryItems(
    @ColumnInfo(name ="itemName")
    var itemName:String="Null",
    @ColumnInfo(name = "itemQuantity")
    var itemQuantity:Int=0,
    @ColumnInfo(name = "itemCost")
    var itemCost:Int=0,
    @ColumnInfo(name = "totalCost")
    var totalCost:Int=0)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
}
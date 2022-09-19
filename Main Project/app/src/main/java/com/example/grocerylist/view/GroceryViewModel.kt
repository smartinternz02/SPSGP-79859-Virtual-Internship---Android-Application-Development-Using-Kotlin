package com.example.grocerylist.view

import androidx.lifecycle.ViewModel
import com.example.grocerylist.database.GroceryRepository
import com.example.grocerylist.database.entity.GroceryItems
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository): ViewModel() {

    @OptIn(DelicateCoroutinesApi::class)
    fun insert(item: GroceryItems) = GlobalScope.launch {
        repository.insert(item)
    }
    @OptIn(DelicateCoroutinesApi::class)
    fun delete(item: GroceryItems) = GlobalScope.launch {
        repository.delete(item)
    }
    fun allGroceryItems() = repository.allGroceryItems()


}

package com.example.grocerylist.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.grocerylist.database.GroceryRepository

class GroceryViewModelFactory(private val repository: GroceryRepository):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GroceryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GroceryViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}
package com.example.marm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marm.database.Coroutines
import com.example.marm.database.DatabaseManager
import com.example.marm.database.Product
import kotlinx.coroutines.launch

class ViewModel: ViewModel(){
    val savedProducts = MutableLiveData<List<Product>>()

    fun saveProduct(product: Product){
        viewModelScope.launch{
            val productDao = DatabaseManager.instance.database.productDao()
            Coroutines(productDao).guardar(product)
        }
    }

    fun deleteUser(product: Product){
        viewModelScope.launch{
            val productDao = DatabaseManager.instance.database.productDao()
            Coroutines(productDao).eliminar(product)
        }
    }

    fun getProducts(){
        viewModelScope.launch{
            val productDao = DatabaseManager.instance.database.productDao()
            savedProducts.value = Coroutines(productDao).getProducts().value
        }
    }
}
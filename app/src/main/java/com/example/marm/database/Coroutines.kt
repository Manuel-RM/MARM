package com.example.marm.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Coroutines(private val productDao: ProductDao) {

    suspend fun guardar(product: Product) = withContext(Dispatchers.IO) {
        productDao.save(product.toEntity())
    }

    suspend fun eliminar(product: Product) = withContext(Dispatchers.IO){
        productDao.delete(product.toEntity())
    }

    suspend fun getProducts(): LiveData<List<Product>> = withContext(Dispatchers.IO){
        return@withContext MutableLiveData(productDao.getProductsFromDatabase().map{it.toProduct()})
    }

    suspend fun getProduct(pid: String){

    }
}
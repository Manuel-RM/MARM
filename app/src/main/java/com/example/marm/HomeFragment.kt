package com.example.marm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.marm.database.Product
import com.example.marm.database.ProductEntity
import com.example.marm.databinding.FragmentHomeBinding
import com.example.marm.retrofit.ProductEntry
import com.example.marm.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), ProductListCallback {
    private val mainViewModel : ViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val database = RetrofitBuilder.create()
            val response = database.getProducts().also {
            it.enqueue(object : Callback<List<ProductEntry>> {
                override fun onResponse(call: Call<List<ProductEntry>>, response : Response<List<ProductEntry>>){
                    Log.e("retrofitResponse", "${response.body()?.get(0)}")
                    var products : List<ProductEntry>? = response.body()
                    if(products != null) {
                        binding.ProductEntries.adapter = AdapterProducts(products, this@HomeFragment)

                        var arrayProducts :Array<ProductEntry> = products.toTypedArray()
                        arrayProducts.shuffle()
                        var listaProducts = arrayProducts.copyOfRange(0,5).toList()


                        binding.ProductEntriesRandom.adapter = AdapterProductsRandom(listaProducts, this@HomeFragment)
                    } else {
                        Log.e("retrofitResponse", "****ES NULL****")
                    }
                }

                override fun onFailure(call: Call<List<ProductEntry>>, t: Throwable){
                    Log.e("retrofitResponse", "error: ${t.message}")
                }
            })
        }

        return binding.root
    }

    fun onClick( id:String, title:String, price : Double, description: String, image : String, rate : Double) {
        mainViewModel.saveProduct(
            Product(
                id,
                title,
                price,
                description,
                image,
                rate,
                "ManuelRM"
            )
        )
        Toast.makeText(context, "Guardado", Toast.LENGTH_LONG).show()
    }

}
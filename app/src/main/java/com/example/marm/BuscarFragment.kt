package com.example.marm

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.viewModels
import com.example.marm.database.Product
import com.example.marm.databinding.FragmentBuscarBinding
import com.example.marm.retrofit.ProductEntry
import com.example.marm.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuscarFragment : Fragment(), ProductListCallback  {
    private val mainViewModel : ViewModel by viewModels()
    private lateinit var binding: FragmentBuscarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBuscarBinding.inflate(layoutInflater)

        //listener
        binding.searchButton.setOnClickListener {
            val database = RetrofitBuilder.create()
            val response = database.getProducts()

            val leido : String = binding.buscar.text.toString()
            if(leido.isNotEmpty() && leido.isDigitsOnly()) {
                response.enqueue(object : Callback<List<ProductEntry>> {
                    override fun onResponse(
                        call: Call<List<ProductEntry>>,
                        response: Response<List<ProductEntry>>
                    ) {
                        Log.e("retrofitResponse", "${response.body()?.get(0)}")
                        var products: List<ProductEntry>? = response.body()
                        if (products != null) {
                            for (products in products) {
                                if(products.id == leido.toInt()) {
                                    var result: List<ProductEntry> = listOf(products)
                                    binding.ProductEntries.adapter = AdapterProducts(result, this@BuscarFragment)
                                    break
                                }
                            }
                        }else {
                            Log.e("retrofitResponse", "****ES NULL****")
                        }
                    }

                    fun onFailure(call: Call<List<ProductEntry>>, t: Throwable) {
                        Log.e("retrofitResponse", "error: ${t.message}")
                    }
                })
            }
        }
        return binding.root
    }

    override fun onClick(
        id: String,
        title: String,
        price: Double,
        description: String,
        image: String,
        rate: Double
    ) {
        mainViewModel.saveProduct(
            Product(
                id,
                title,
                price,
                description,
                image,
                rate
            )
        )
        Toast.makeText(context, "Guardado", Toast.LENGTH_LONG).show()
    }
}
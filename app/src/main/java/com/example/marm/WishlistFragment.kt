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
import com.example.marm.databinding.FragmentWishlistBinding

class WishlistFragment : Fragment(), ProductListCallback {
    private lateinit var binding: FragmentWishlistBinding
    private val mainViewModel : ViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWishlistBinding.inflate(layoutInflater)


        mainViewModel.getProducts()
        //si es fragment
        //es con viewLifecycleOwner en lugar de this

        mainViewModel.savedProducts.observe(viewLifecycleOwner) { productsList ->
            if (productsList.isNotEmpty()) {
                Log.i("Estos son los usuarios", "${productsList.toString()} or empty")
                binding.wishlistEntries.adapter = AdapterProducts(productsList, this@WishlistFragment)
            } else {
                Log.e("Estos son los usuarios", "null or empty")
            }
        }

        return binding.root
    }

     fun onClick(
        id: String,
        title: String,
        price: Double,
        description: String,
        image: String,
        rate: Double
    ) {
        mainViewModel.deleteUser(
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
        Toast.makeText(context, "Eliminado", Toast.LENGTH_LONG).show()
        mainViewModel.getProducts()
        //si es fragment
        //es con viewLifecycleOwner en lugar de this

        mainViewModel.savedProducts.observe(viewLifecycleOwner) { productsList ->
            if (productsList.isNotEmpty()) {
                Log.i("Estos son los usuarios", "${productsList.toString()} or empty")
                binding.wishlistEntries.adapter = AdapterProducts(productsList, this@WishlistFragment)
            } else {
                Log.e("Estos son los usuarios", "null or empty")
            }
        }

    }

}
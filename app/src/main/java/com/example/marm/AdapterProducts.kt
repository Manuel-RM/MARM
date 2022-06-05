package com.example.marm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marm.database.Product
import com.example.marm.databinding.ProductBinding
import com.example.marm.retrofit.ProductEntry
import com.squareup.picasso.Picasso

class AdapterProducts(private val product: List<Product>, val callBack: ProductListCallback): RecyclerView.Adapter<AdapterProducts.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = ProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainHolder(binding, callBack)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.render(product.get(position))
    }

    override fun getItemCount(): Int {

        return product.size
    }

    class MainHolder(val binding:ProductBinding, val callBack: ProductListCallback):RecyclerView.ViewHolder(binding.root) {
        fun render(product: ProductEntry) {
            //TODDO assign text and image values in render function
            binding.nombre.setText(product.title)
            binding.precio.setText("$ ${product.price}")
            binding.descripcion.setText(product.description)
            Picasso.get().load("${product.image}").into(binding.product)
            binding.agregarButton.setOnClickListener{
                callBack.onClick(product.id.toString(),
                    product.name,
                    product.price,
                    product.description,
                    product.image,
                    product.rating.rate,
                )
            }
        }
    }


}

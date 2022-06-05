package com.example.marm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marm.databinding.ProductRandomBinding
import com.example.marm.retrofit.ProductEntry
import com.squareup.picasso.Picasso

class AdapterProductsRandom (private val products: List<ProductEntry>, val callBack: ProductListCallback): RecyclerView.Adapter<AdapterProductsRandom.MainHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterProductsRandom.MainHolder {
        val binding = ProductRandomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdapterProductsRandom.MainHolder(binding, callBack)
    }

    override fun onBindViewHolder(holder: AdapterProductsRandom.MainHolder, position: Int) {
        holder.render(products.get(position))
    }

    override fun getItemCount(): Int {
        return products.size
    }

    class MainHolder(val binding:ProductRandomBinding, val callBack: ProductListCallback):RecyclerView.ViewHolder(binding.root){

        fun render(product: ProductEntry) {
            //TODDO assign text and image values in render function
            binding.product.setText(product.name)
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
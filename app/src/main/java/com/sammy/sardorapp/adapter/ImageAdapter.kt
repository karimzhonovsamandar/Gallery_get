package com.sammy.sardorapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import com.sammy.sardorapp.databinding.ItemRvBinding
import com.sammy.sardorapp.models.User
import androidx.recyclerview.widget.RecyclerView


class ImageAdapter(val list: ArrayList<User>) : RecyclerView.Adapter<ImageAdapter.Vh>() {

    inner class Vh(var itemRvBinding: ItemRvBinding ) : RecyclerView.ViewHolder(itemRvBinding.root) {

        fun onBind(user: User, position: Int) {

            itemRvBinding.imageView.setImageURI(user.image.toUri())
            itemRvBinding.textNomi.text = user.name

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)

    }

}
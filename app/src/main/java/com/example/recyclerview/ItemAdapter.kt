package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemBinding

class ItemAdapter(private val itemList: ArrayList<Item>, val listener: OnItemClickListener): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val itemBinding: ItemBinding): RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener{

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(item: Item){
            itemBinding.imageIV.setImageResource(item.imagem)
            itemBinding.tituloTV.text = item.titulo
            itemBinding.infoTV.text = item.info
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = itemList.size

    fun deleteItem(position: Int){
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun editItem(position: Int, item: Item){
        itemList.set(position, item)
        notifyItemChanged(position)
    }

}


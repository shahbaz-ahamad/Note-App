package com.example.mvvm.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.databinding.EachItemBinding
import com.example.mvvm.model.User

class adapter(val context:Context, var list:ArrayList<User>,val onItemClickListener: OnItemClickListener):RecyclerView.Adapter<adapter.ViewHolder>() {
    class ViewHolder(val binding:EachItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(EachItemBinding.inflate(LayoutInflater.from(context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.name.text=list[position].name
        holder.binding.age.text=list[position].age
        holder.binding.delete.setOnClickListener {
            onItemClickListener.onDeleteClick(list[position])
        }

        holder.binding.update.setOnClickListener {
            onItemClickListener.onUpdateClick(list[position])
        }

    }

    fun setData(list: ArrayList<User>){
        this.list=list
        notifyDataSetChanged()

    }

    interface OnItemClickListener {
        fun onDeleteClick(user: User)
        fun onUpdateClick(user:User)
    }
}
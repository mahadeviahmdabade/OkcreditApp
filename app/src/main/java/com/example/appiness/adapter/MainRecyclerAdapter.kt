package com.example.appiness.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appiness.R
import com.example.appiness.model.ItemsItem

class MainRecyclerAdapter(
    private val list: List<ItemsItem>,
    private val layoutInflater: LayoutInflater
) : RecyclerView.Adapter<MainAdapterViewHolder>() {

    override fun onBindViewHolder(holder: MainAdapterViewHolder, position: Int) {
        holder.title.text = list[position].login
        holder.by.text = list[position].id.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapterViewHolder {
        return MainAdapterViewHolder(layoutInflater.inflate(R.layout.item, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class MainAdapterViewHolder(views: View) : RecyclerView.ViewHolder(views) {
    val title = views.findViewById<TextView>(R.id.title)
    val by = views.findViewById<TextView>(R.id.id)
}
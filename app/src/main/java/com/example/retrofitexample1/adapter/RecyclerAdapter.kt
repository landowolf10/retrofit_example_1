package com.example.retrofitexample1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexample1.R
import com.example.retrofitexample1.model.Repo
import java.util.zip.Inflater

class RecyclerAdapter(private var items: MutableList<Repo>):RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_view,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setNamesList(items: MutableList<Repo>)
    {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        fun render(item: Repo)
        {
            var projectID: TextView = itemView.findViewById(R.id.project_id)
            var projectName: TextView = itemView.findViewById(R.id.project_name)

            projectID.text = item.id.toString()
            projectName.text = item.name
        }
    }
}
package com.example.retrofitexample1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexample1.R
import com.example.retrofitexample1.model.Repo

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>()
{
    private var repos: List<Repo> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_view,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(repos[position])
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    fun setNamesList(items: MutableList<Repo>)
    {
        this.repos = items
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

            itemView.setOnClickListener {
                val  position: Int = adapterPosition

                Toast.makeText(itemView.context, "REPO id: ${item.id}, Repo name: " +
                        "${item.name}, Repo position: ${position + 1}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
package com.spencerpotter.usu_cs_app

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spencerpotter.usu_cs_app.databinding.ResearchlistBinding

var countResearch = 0

class ResearchAdapter( val researches: List<Research> ): RecyclerView.Adapter<ResearchAdapter.ViewHolder>() {
    class ViewHolder(val binding: ResearchlistBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        countResearch++
        val binding = ResearchlistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val research = researches[position]
        holder.binding.researchName.text = research.name
        holder.binding.description.text = research.description
        holder.binding.facultyEngaged.text = research.facultyEngaged
    }

    override fun getItemCount(): Int {
        return researches.size
    }
}
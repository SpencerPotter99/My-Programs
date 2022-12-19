package com.spencerpotter.usu_cs_app
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.spencerpotter.usu_cs_app.databinding.FacultymemberBinding

var count = 0;

class FacultyMember (val faculty: List<Faculty> ):RecyclerView.Adapter<FacultyMember.ViewHolder>(){
    class ViewHolder(val binding: FacultymemberBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FacultyMember.ViewHolder {
        count++
        val binding = FacultymemberBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FacultyMember.ViewHolder, position: Int) {
        val member = faculty[position]
        holder.binding.Name.text = member.name
        holder.binding.Title.text = member.title
        holder.binding.office.text = member.officeLocation
    }

    override fun getItemCount(): Int {
        return faculty.size
    }


}
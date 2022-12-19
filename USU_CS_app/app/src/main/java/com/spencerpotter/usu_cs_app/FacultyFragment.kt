package com.spencerpotter.usu_cs_app

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.spencerpotter.usu_cs_app.databinding.FragmentFacultyBinding
import com.spencerpotter.usu_cs_app.databinding.FragmentHomeBinding
import com.spencerpotter.usu_cs_app.databinding.FragmentResearchBinding

data class Faculty(
    val name: String,
    val title: String,
    val officeLocation: String
)

class FacultyFragment : Fragment() {
    override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        val faculty1 = Faculty("Mahdi Nasrullah Al-Ameen", "Assistant Professor", "Old Main 410F")
        val faculty2 = Faculty("Vicki Allan", "Associate Professor", "Old Main 429")
        val faculty3 = Faculty("Soukaina Filali Boubrahimi", "Assistant Professor", "Old Main 401A")
        val faculty4 = Faculty("Heng-Da Cheng", "Professor", "Old Main 401B")
        val faculty5 = Faculty("Isaac Cho", "Assistant Professor", "Old Main 402G")
        val faculty6 = Faculty("Stephen Clyde", "Emeritus Associate Professor", "Old Main 418")
        val faculty7 = Faculty("Joseph Dittion aka the Goat", "Professional Practice Assistant Professor",
            "Old Main 420")
        val faculty8 = Faculty("Curtis Dyreson", "Professor", "Old Main 402A")
        val faculty9 = Faculty("John Edwards", "Assistant Professor", "Old Main 401D")
        val faculty10 = Faculty("Erik Falor", "Professional Practice Associate Professor", "Old Main 418A")
        val binding = FragmentFacultyBinding.inflate(inflater, container, false)
        val facultys = mutableListOf<Faculty>(faculty1,faculty2,faculty3,faculty4,faculty5,faculty6,
            faculty7, faculty8, faculty9, faculty10)

        binding.goToH.setOnClickListener{
            findNavController().navigate(R.id.action_facultyFragment_to_homeFragment)
        }
        binding.facultyRecycler.adapter = FacultyMember(facultys)

        binding.facultyRecycler.layoutManager = LinearLayoutManager(context)
        return binding.root
    }
}
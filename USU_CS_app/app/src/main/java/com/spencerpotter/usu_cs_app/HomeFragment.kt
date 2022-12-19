package com.spencerpotter.usu_cs_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.spencerpotter.usu_cs_app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View?{
    val binding = FragmentHomeBinding.inflate(inflater, container, false)
    binding.goToFaculty.setOnClickListener{
        findNavController().navigate(R.id.action_homeFragment_to_facultyFragment)
    }
    binding.goToResearch.setOnClickListener{
        findNavController().navigate(R.id.action_homeFragment_to_researchFragment)
    }
    return binding.root
    }
}
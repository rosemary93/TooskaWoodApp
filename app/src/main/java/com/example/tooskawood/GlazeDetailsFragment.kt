package com.example.tooskawood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tooskawood.databinding.FragmentGlazeDetailsBinding


class GlazeDetailsFragment : Fragment() {
    lateinit var binding: FragmentGlazeDetailsBinding
    var glazeID = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            glazeID = it.getInt("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGlazeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (glazeID==-1){
            binding.llAddGlaze.visibility=View.VISIBLE
            binding.buttonAddIngredient.isEnabled=false
        }else{
            binding.llAddGlaze.visibility=View.GONE

        }
    }


}
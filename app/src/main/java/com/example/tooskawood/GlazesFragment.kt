package com.example.tooskawood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tooskawood.databinding.FragmentGlazesBinding


class GlazesFragment : Fragment() {
    lateinit var binding: FragmentGlazesBinding
    val vmodel: MainViewModel by viewModels()
    var mAdapterGlaze:GlazeListAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGlazesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapterGlaze = GlazeListAdapter(vmodel.getAllGlazes())
        binding.rvGlazes.adapter = mAdapterGlaze

       /* vmodel.glazeListLivedata?.observe(viewLifecycleOwner) {

            if (it != null) {
                adapter = ListAdapter(it)
                binding.rvGlazes.adapter = adapter
            }

        }*/
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_glazesFragment_to_glazeDetailsFragment)
        }

    }

}
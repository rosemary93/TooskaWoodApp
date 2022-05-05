package com.example.tooskawood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.tooskawood.database.Glaze
import com.example.tooskawood.database.Ingredients
import com.example.tooskawood.databinding.FragmentGlazeDetailsBinding


class GlazeDetailsFragment : Fragment() {
    lateinit var binding: FragmentGlazeDetailsBinding
    val vmodel: MainViewModel by viewModels()
    var glazeID = -1
    lateinit var glaze: Glaze
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
        if (glazeID == -1) {
            binding.llAddGlaze.visibility = View.VISIBLE
            binding.llAddIngredient.visibility = View.GONE
            binding.rvIngredients.visibility = View.GONE
        } else {
            binding.llAddGlaze.visibility = View.GONE
            binding.llAddIngredient.visibility = View.VISIBLE
            binding.rvIngredients.visibility = View.VISIBLE
            glaze=vmodel.findGlazeBiID(glazeID)
        }

        binding.buttonAddGlaze.setOnClickListener {
            if (areValidGlazeFields()) {
                binding.llAddGlaze.visibility = View.GONE
                binding.llAddIngredient.visibility = View.VISIBLE
                binding.rvIngredients.visibility = View.VISIBLE
                val tempGlaze=Glaze(binding.editTextGlazeId.text.toString().toInt(),
                binding.editTextGlazeName.text.toString(), arrayListOf())
                vmodel.addGlaze(tempGlaze)
                glazeID=tempGlaze.id
                glaze=vmodel.findGlazeBiID(glazeID)
            }

        }

        binding.buttonAddIngredient.setOnClickListener {

            if (areValidIngredientFields()){
                val glazeIngredients=glaze.ingredientList as ArrayList<Ingredients>
                var tempCode="0"
                var tempDescr="empty"
                if (!binding.editTextIngredientCode.text.isNullOrBlank())
                    tempCode=binding.editTextIngredientCode.text.toString()
                if (!binding.editTextIngredientDescription.text.isNullOrBlank())
                    tempDescr=binding.editTextIngredientDescription.text.toString()
                glazeIngredients.add(Ingredients(binding.editTextIngredient.text.toString(),
                binding.editTextIngredientAmount.text.toString(),
                tempCode,tempDescr))

                glaze= Glaze(glaze.id,glaze.name,glazeIngredients)
                vmodel.updateGlaze(glaze)
            }
        }
    }

    private fun areValidGlazeFields(): Boolean {
        if (binding.editTextGlazeId.text.isNullOrBlank()) {
            binding.editTextGlazeId.error = "fill here"
            return false
        }
        if (binding.editTextGlazeName.text.isNullOrBlank()) {
            binding.editTextGlazeName.error = "fill here"
            return false
        }
        for (glaze in vmodel.getAllGlazes()?.value!!) {
            if (glaze?.id == binding.editTextGlazeId.text.toString().toInt()) {
                Toast.makeText(
                    requireContext(),
                    "This id exists, please choose another number.",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }

        return true
    }

    private fun areValidIngredientFields():Boolean{
        if (binding.editTextIngredient.text.isNullOrBlank()){
            binding.editTextIngredient.error="fill here"
            return false
        }

        if (binding.editTextIngredientAmount.text.isNullOrBlank())
        {
            binding.editTextIngredientAmount.error="fill here"
            return false
        }
        return true
    }

}
package com.example.tooskawood.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tooskawood.R
import com.example.tooskawood.database.Glaze
import com.example.tooskawood.database.Ingredients
import com.example.tooskawood.databinding.FragmentEditIngredientBinding
import com.example.tooskawood.viewmodel.MainViewModel


class EditIngredientFragment : Fragment() {
    lateinit var binding: FragmentEditIngredientBinding
    private val vmodel: MainViewModel by viewModels()
    var glazeId = -1
    var ingredientPosition = -1
    var ingredientName=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val argStrArr = it.getString("glazeInfo")!!.split(',')
            glazeId = argStrArr[0].toInt()
            ingredientName=argStrArr[1]

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditIngredientBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var glaze = vmodel.findGlazeBiID(glazeId)
        ingredientPosition = findIngredientByName(ingredientName,glaze)
        val glazeIngredient = glaze.ingredientList[ingredientPosition]
        val glazeIngredientList = glaze.ingredientList as ArrayList<Ingredients>
        binding.etIngName.setText(glazeIngredient.ingredientName)
        binding.etIngAmount.setText(glazeIngredient.amount)
        binding.etIngCode.setText(glazeIngredient.code)
        binding.etIngDesc.setText(glazeIngredient.description)


        binding.btnEditIng.setOnClickListener {
            if (areValidIngredientFields()) {

                var tempCode = "0"
                var tempDescr = "فاقد توضیحات"
                if (!binding.etIngCode.text.isNullOrBlank())
                    tempCode = binding.etIngCode.text.toString()
                if (!binding.etIngDesc.text.isNullOrBlank())
                    tempDescr = binding.etIngDesc.text.toString()

                glazeIngredientList[ingredientPosition] = (
                        Ingredients(
                            binding.etIngName.text.toString(),
                            binding.etIngAmount.text.toString(),
                            tempCode, tempDescr
                        )
                        )
                glaze = Glaze(glazeId, glaze.name, glazeIngredientList)
                vmodel.updateGlaze(glaze)
                val action=EditIngredientFragmentDirections.actionEditIngredientFragmentToGlazeDetailsFragment(glazeId)
                findNavController().navigate(action)
            }
        }

        binding.btnDeleteIng.setOnClickListener {
            glazeIngredientList.removeAt(ingredientPosition)
            vmodel.updateGlaze(Glaze(glazeId, glaze.name, glazeIngredientList))
            val action=EditIngredientFragmentDirections.actionEditIngredientFragmentToGlazeDetailsFragment(glazeId)
            findNavController().navigate(action)
        }


    }

    private fun areValidIngredientFields(): Boolean {
        if (binding.etIngName.text.isNullOrBlank()) {
            binding.etIngName.error = "فیلد اجباری"
            return false
        }

        if (binding.etIngAmount.text.isNullOrBlank()) {
            binding.etIngAmount.error = "فیلد اجباری"
            return false
        }
        return true
    }

    fun findIngredientByName(name:String,glaze: Glaze):Int{
        var index=0
        for (i in 0 until glaze.ingredientList.size)
        {
            if (glaze.ingredientList[i].ingredientName==name)
                return i
        }
        return index
    }

}
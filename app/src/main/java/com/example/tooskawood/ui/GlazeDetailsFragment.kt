package com.example.tooskawood.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tooskawood.R
import com.example.tooskawood.viewmodel.MainViewModel
import com.example.tooskawood.database.Glaze
import com.example.tooskawood.database.Ingredients
import com.example.tooskawood.databinding.FragmentGlazeDetailsBinding


class GlazeDetailsFragment : Fragment() {
    lateinit var binding: FragmentGlazeDetailsBinding
    var adapter: IngredientListAdapter?=null
    private val vmodel: MainViewModel by viewModels()
    private var recievedGlazeID = -1
    var glaze: Glaze?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recievedGlazeID = it.getInt("id")
            if (recievedGlazeID!=-1){
                glaze=vmodel.findGlazeBiID(recievedGlazeID)
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // in here you can do logic when backPress is clicked
                updateConvertedAmount()
                findNavController().navigate(R.id.action_glazeDetailsFragment_to_glazesFragment)

            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGlazeDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vmodel.glazeListLivedata?.observe(viewLifecycleOwner) {}
        initViews()
        setButtonsListeners()


    }

    private fun initViews() {
        adapter = glaze?.ingredientList?.let { IngredientListAdapter(it) }
        binding.rvIngredients.adapter = adapter

        if (recievedGlazeID == -1) {
            binding.llAddGlaze.visibility = View.VISIBLE
            binding.llAddIngredient.visibility = View.GONE
            binding.rvIngredients.visibility = View.GONE
            binding.llConvert.visibility=View.GONE
        } else {
            binding.llAddGlaze.visibility = View.GONE
            binding.llAddIngredient.visibility = View.VISIBLE
            binding.rvIngredients.visibility = View.VISIBLE
            glaze=vmodel.findGlazeBiID(recievedGlazeID)
        }
    }

    private fun setButtonsListeners(){
        binding.buttonAddGlaze.setOnClickListener {
            if (areValidGlazeFields()) {
                binding.llAddGlaze.visibility = View.GONE
                binding.llAddIngredient.visibility = View.VISIBLE
                binding.rvIngredients.visibility = View.VISIBLE
                binding.llConvert.visibility=View.VISIBLE
                val tempGlaze=Glaze(binding.editTextGlazeId.text.toString().toInt(),
                    binding.editTextGlazeName.text.toString(), arrayListOf())
                vmodel.addGlaze(tempGlaze)
                glaze=vmodel.findGlazeBiID(tempGlaze.id)

            }

        }

        binding.buttonAddIngredient.setOnClickListener {

            if (areValidIngredientFields()){
                val glazeIngredients=glaze?.ingredientList as ArrayList<Ingredients>
                var tempCode="0"
                var tempDescr="فاقد توضیحات"
                if (!binding.editTextIngredientCode.text.isNullOrBlank())
                    tempCode=binding.editTextIngredientCode.text.toString()
                if (!binding.editTextIngredientDescription.text.isNullOrBlank())
                    tempDescr=binding.editTextIngredientDescription.text.toString()
                glazeIngredients.add(Ingredients(binding.editTextIngredient.text.toString(),
                    binding.editTextIngredientAmount.text.toString(),
                    tempCode,tempDescr))

                adapter = IngredientListAdapter(glazeIngredients)
                binding.rvIngredients.adapter = adapter


                glaze= glaze?.let { it1 -> Glaze(it1.id, glaze!!.name,glazeIngredients) }
                glaze?.let { it1 -> vmodel.updateGlaze(it1) }

                clearEditTexts()
            }
        }

        binding.btnConvert.setOnClickListener {
            if (glaze?.ingredientList?.isNotEmpty()!!)
            {
                if (!binding.etScale.text.isNullOrBlank()) {
                    val tempGlaze=vmodel.getConvertedGlaze(glaze!!.id, binding.etScale.text.toString().toDouble())
                    vmodel.updateGlaze(tempGlaze)

                    adapter = IngredientListAdapter(tempGlaze.ingredientList)
                    binding.rvIngredients.adapter = adapter

                }
            }
        }

    }

    private fun areValidGlazeFields(): Boolean {
        if (binding.editTextGlazeId.text.isNullOrBlank()) {
            binding.editTextGlazeId.error = "فیلد اجباری"
            return false
        }
        if (binding.editTextGlazeName.text.isNullOrBlank()) {
            binding.editTextGlazeName.error = "فیلد اجباری"
            return false
        }
        for (glaze in vmodel.glazeListLivedata?.value!!) {
            if (glaze?.id == binding.editTextGlazeId.text.toString().toInt()) {
                Toast.makeText(
                    requireContext(),
                    "این کد لعاب موجود است لطفا کد جدید وارد کنید",
                    Toast.LENGTH_SHORT
                ).show()
                return false
            }
        }

        return true
    }

    private fun areValidIngredientFields():Boolean{
        if (binding.editTextIngredient.text.isNullOrBlank()){
            binding.editTextIngredient.error="فیلد اجباری"
            return false
        }

        if (binding.editTextIngredientAmount.text.isNullOrBlank())
        {
            binding.editTextIngredientAmount.error="فیلد اجباری"
            return false
        }
        return true
    }

    private fun clearEditTexts(){
        binding.editTextIngredient.setText(" ")
        binding.editTextIngredientAmount.setText(" ")
        binding.editTextIngredientCode.setText(" ")
        binding.editTextIngredientDescription.setText(" ")
    }

     fun updateConvertedAmount(){
        val tempGlaze=glaze
        if (tempGlaze != null) {
            for (ingredient in tempGlaze.ingredientList)
                ingredient.convertedAmount=" "
        }
        if (tempGlaze != null) {
            vmodel.updateGlaze(tempGlaze)
        }
    }



}

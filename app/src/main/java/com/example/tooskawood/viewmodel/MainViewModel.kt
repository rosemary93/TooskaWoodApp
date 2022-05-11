package com.example.tooskawood.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tooskawood.repositories.GlazeRepository
import com.example.tooskawood.database.Glaze
import com.example.tooskawood.database.Ingredients

class MainViewModel(app: Application) : AndroidViewModel(app){

    var glazeListLivedata: LiveData<List<Glaze?>?>?

    init {
        GlazeRepository.initDB(app)
        glazeListLivedata = GlazeRepository.getGlazeList()
    }

    fun  findGlazeBiID(id:Int):Glaze{
        return GlazeRepository.findGlaze(id)
    }

    fun getAllGlazes():LiveData<List<Glaze?>?>?{
        return GlazeRepository.getGlazeList()
    }

    fun updateGlaze(glaze: Glaze){
        GlazeRepository.updateGlaze(glaze)
    }

    fun addGlaze(glaze: Glaze){
        GlazeRepository.addGlaze(glaze)
    }

    fun addTestData(){
        val ingrd = arrayListOf<Ingredients>(
            Ingredients("eee", "10.0"),
            Ingredients("fff", "50.0"),
            Ingredients("ggg", "20.0"),
            Ingredients("hhh", "10.0")
        )

        addGlaze(Glaze(5, "E", ingrd))
        addGlaze(Glaze(6, "F", ingrd))
        addGlaze(Glaze(7, "G", ingrd))
        addGlaze(Glaze(8, "H", ingrd))
    }

    fun getConvertedGlaze(glazeId:Int, scale:Double):Glaze{
        val glaze=findGlazeBiID(glazeId)
        var total=0.0
        for(ingredient in glaze.ingredientList){
            total+=ingredient.amount.toDouble()
        }
        val ingredientList=glaze.ingredientList
        for(ingredient in ingredientList){
            val converted=(ingredient.amount.toDouble()*scale)/total
            ingredient.convertedAmount=converted.toString()
        }
        return Glaze(glaze.id,glaze.name,ingredientList)

    }
}
package com.example.tooskawood

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.tooskawood.database.Glaze
import com.example.tooskawood.database.Ingredients

class MainViewModel(app: Application) : AndroidViewModel(app){

    var glazeListLivedata: List<Glaze>

    init {
        GlazeRepository.initDB(app)
        glazeListLivedata = GlazeRepository.getGlazeList()
    }

    fun  findGlazeBiID(id:Int):Glaze{
        return GlazeRepository.findGlaze(id)
    }

    fun getAllGlazes():List<Glaze>{
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
}
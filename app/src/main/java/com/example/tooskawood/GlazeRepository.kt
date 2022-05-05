package com.example.tooskawood

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.tooskawood.database.AppDatabase
import com.example.tooskawood.database.Glaze
import com.example.tooskawood.database.GlazeDao

object GlazeRepository {
    lateinit var glazeDao: GlazeDao
    lateinit var glazeList: List<Glaze>
    fun initDB(context: Context) {
        val db = AppDatabase.getAppDataBase(context)
        glazeDao = db!!.glazeDao()
        glazeList = glazeDao.getAllGlazes()
    }

    fun findGlaze(id:Int):Glaze{
        return glazeDao.findGlazeById(id)
    }

    @JvmName("getGlazeList1")
    fun getGlazeList():List<Glaze>{
        return glazeDao.getAllGlazes()
    }

    fun updateGlaze(glaze: Glaze){
        glazeDao.updateGlaze(glaze)
    }

    fun addGlaze(glaze: Glaze){
        glazeDao.insertGlaze(glaze)
    }

}
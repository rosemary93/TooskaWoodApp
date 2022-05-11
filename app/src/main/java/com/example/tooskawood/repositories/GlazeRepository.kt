package com.example.tooskawood.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.tooskawood.database.AppDatabase
import com.example.tooskawood.database.Glaze
import com.example.tooskawood.database.GlazeDao

object GlazeRepository {
    lateinit var glazeDao: GlazeDao
    var glazeList: LiveData<List<Glaze?>?>?=null
    fun initDB(context: Context) {
        val db = AppDatabase.getAppDataBase(context)
        glazeDao = db!!.glazeDao()
        glazeList = glazeDao.getAllGlazes()
    }

    fun findGlaze(id:Int):Glaze{
        return glazeDao.findGlazeById(id)
    }

    @JvmName("getGlazeList1")
    fun getGlazeList():LiveData<List<Glaze?>?>?{
        return glazeDao.getAllGlazes()
    }

    fun updateGlaze(glaze: Glaze){
        glazeDao.updateGlaze(glaze)
    }

    fun addGlaze(glaze: Glaze){
        glazeDao.insertGlaze(glaze)
    }

}
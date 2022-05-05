package com.example.tooskawood.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GlazeDao {
    @Query("SELECT * FROM Glaze WHERE id IN (:id)")
    fun findGlazeById(id:Int):Glaze

    @Query("SELECT * FROM Glaze")
    fun getAllGlazes(): LiveData<List<Glaze?>?>?

    @Insert
    fun insertGlaze(glaze: Glaze)

    @Update
    fun updateGlaze(glaze: Glaze)
}
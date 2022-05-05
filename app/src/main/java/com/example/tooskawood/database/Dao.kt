package com.example.tooskawood.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface GlazeDao {
    @Query("SELECT * FROM Glaze WHERE id IN (:id)")
    fun findGlazeById(id:Int)

    @Query("SELECT * FROM Glaze")
    fun getAllWords(): LiveData<List<Glaze?>?>?

    @Insert
    fun insertWord(glaze: Glaze)
}
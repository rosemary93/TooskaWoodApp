package com.example.tooskawood.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Glaze(
    @PrimaryKey var id: Int,
    var name: String,
    var ingredientList: List<Ingredients>
)


data class Ingredients(
    var ingredientName: String,
    var amount: String,
    var code: String = "0",
    var description: String = "فاقد توضیحات",
    var convertedAmount:String=" "
)


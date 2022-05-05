package com.example.tooskawood.database

import androidx.room.Entity

@Entity
data class Glaze(var id:Int,var name:String,var ingredientList:List<Ingredients>)


class Ingredients(var ingredientName:String,var amount:Double,
                var code:Int=0,
                var description:String="")
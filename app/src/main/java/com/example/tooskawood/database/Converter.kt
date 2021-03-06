package com.example.tooskawood.database

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converter {
    @TypeConverter
    fun stringFromIngredientList(ingredientList:List<Ingredients>):String{
        var str=""
        for (ingredient in ingredientList)
        {
            str+=ingredient.ingredientName+","+ingredient.amount+","+ingredient.code+","+ingredient.description+"-"
        }
        //str=str.substring(0,str.length-2)
        return str
    }

    @TypeConverter
    fun stringToIngredientList(ingredientsString:String):List<Ingredients>{
        var list= arrayListOf<Ingredients>()
        var strs=ingredientsString.split('-')
        for (str in strs)
        {
            if (str.isNullOrEmpty())
                break
            var item=str.split(',')
            val tempIngr=Ingredients(item[0],item[1],item[2],item[3])
            list.add(tempIngr)
        }
        return list
    }
}
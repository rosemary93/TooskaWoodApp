package com.example.tooskawood.database

data class Glaze(var id:Int,var name:String,)
class GlazeInfo(var ingredient:String,var amount:Double,
                var code:Int=0,
                var description:String="")
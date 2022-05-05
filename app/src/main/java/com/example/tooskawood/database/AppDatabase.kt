package com.example.tooskawood.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Glaze::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase  : RoomDatabase() {
    abstract fun glazeDao(): GlazeDao

    companion object {
        var INSTANCE: AppDatabase? = null


        fun getAppDataBase(context: Context): AppDatabase? {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(AppDatabase::class) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "GlazesDB"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                INSTANCE = instance
                return instance
            }
        }

    }

    fun destroyDataBase() {
        INSTANCE = null
    }
}


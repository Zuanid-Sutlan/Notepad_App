package com.example.notepadapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 2, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object{

        private var INSTANCE : MyDataBase?=null

        fun getDataBase(context: Context) : MyDataBase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                    MyDataBase::class.java, "MyDatabase").enableMultiInstanceInvalidation().build()

                INSTANCE= instance
                instance
            }

        }
    }

}
package com.example.mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm.dao.DAO
import com.example.mvvm.model.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase:RoomDatabase(){

    abstract fun getDao():DAO//this is useed to called the method of the dao interface from the rpository class

    //to create the singleton object we can user companion object

    companion object{
        private const val DATABASENAME="User_database"

        @Volatile //to delete the object of the database once application is closed
        var instance:UserDatabase?=null

        fun getInstance(context: Context):UserDatabase?{

            if(instance==null){
                synchronized(UserDatabase::class.java){
                    instance= Room.databaseBuilder(context,UserDatabase::class.java, DATABASENAME)
                        .build()
                }
            }

            return instance
        }
    }
}
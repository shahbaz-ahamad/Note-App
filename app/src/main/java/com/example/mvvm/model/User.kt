package com.example.mvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,val age:String)
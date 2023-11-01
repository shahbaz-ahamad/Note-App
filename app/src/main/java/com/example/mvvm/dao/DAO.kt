package com.example.mvvm.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mvvm.model.User


@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)//the parameter will replace the duplicate data
    fun insert(user:User)

    @Query("SELECT * FROM user")
    fun getAllUserData():LiveData<List<User>>

    @Delete
    fun delete(user: User)

    @Update
    fun update(user:User)
}
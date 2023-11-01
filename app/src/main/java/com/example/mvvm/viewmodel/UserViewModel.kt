package com.example.mvvm.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvm.model.User
import com.example.mvvm.repository.UserRepo

class UserViewModel:ViewModel() {

    fun insert(context: Context,user: User){
        UserRepo.insert(context,user)
    }

    fun getAllUserData(context: Context):LiveData<List<User>>?{
        return UserRepo.getAllUserData(context)
    }

    fun delete(context: Context,user: User){
        UserRepo.delete(context,user)
    }

    fun update(context: Context,user: User){
        UserRepo.update(context,user)

    }
}
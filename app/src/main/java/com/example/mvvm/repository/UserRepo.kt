package com.example.mvvm.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.mvvm.database.UserDatabase
import com.example.mvvm.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UserRepo {


    companion object{

        private var userDatabase:UserDatabase?=null

        fun initDB(context: Context):UserDatabase?{
            return UserDatabase.getInstance(context)
        }
        fun insert(context: Context,user: User){
                userDatabase= initDB(context)

            CoroutineScope(IO).launch {
                userDatabase?.getDao()?.insert(user)
            }

        }

        fun getAllUserData(context: Context):LiveData<List<User>>?{
            userDatabase= initDB(context)
            return userDatabase?.getDao()?.getAllUserData()
        }

        fun delete(context: Context, user: User){
            userDatabase= initDB(context)
            CoroutineScope(IO).launch {
                userDatabase?.getDao()?.delete(user)
            }

        }

        fun update(context: Context,user: User){
            userDatabase= initDB(context)
            CoroutineScope(IO).launch {
                userDatabase?.getDao()?.update(user)
            }
        }
    }

}
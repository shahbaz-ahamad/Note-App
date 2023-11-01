package com.example.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.adapter.adapter
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.databinding.DailogueBinding
import com.example.mvvm.model.User
import com.example.mvvm.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(),adapter.OnItemClickListener{

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var builder:AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var alertDialogBinding:DailogueBinding
    private lateinit var adapter:adapter
    private lateinit var onItemClickListener: adapter.OnItemClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //for the interface
        onItemClickListener=this


        binding.floatingButton.setOnClickListener{
            openDialogue()
        }


        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        userViewModel.getAllUserData(applicationContext)?.observe(this, Observer {
            adapter.setData(it as ArrayList<User>)
        })

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager=LinearLayoutManager(this)
        adapter= adapter(this,ArrayList<User>(),onItemClickListener)
        binding.recyclerView.adapter=adapter




    }

    private fun openDialogue() {
            builder=AlertDialog.Builder(this)
            alertDialogBinding=DailogueBinding.inflate(layoutInflater)
            builder.setView(alertDialogBinding.root)
            dialog=builder.create()

            alertDialogBinding.add.setOnClickListener{
                val name=alertDialogBinding.name.text.toString()
                val age=alertDialogBinding.age.text.toString()
                addDataToRoomDataBase(name,age)

            }

        dialog.show()

    }

    private fun addDataToRoomDataBase(name: String, age: String) {
        userViewModel.insert(this,User(0,name,age))
        dialog.dismiss()
    }

    override fun onDeleteClick(user: User) {

        userViewModel.delete(applicationContext,user)


    }

    override fun onUpdateClick(user: User) {
        builder=AlertDialog.Builder(this)
        alertDialogBinding=DailogueBinding.inflate(layoutInflater)
        builder.setView(alertDialogBinding.root)
        dialog=builder.create()
        alertDialogBinding.add.text="Update"

        //setting the data
        alertDialogBinding.name.setText(user.name)
        alertDialogBinding.age.setText(user.age)
        dialog.show()

        alertDialogBinding.add.setOnClickListener {
            userViewModel.update(applicationContext,User(user.id,alertDialogBinding.name.text.toString(),alertDialogBinding.age.text.toString()))
            dialog.dismiss()
        }



    }
}
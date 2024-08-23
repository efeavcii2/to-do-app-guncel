package com.example.to_do_project.data

data class tododata(
    val todoList: List<String> = emptyList(),
    val isLoading:Boolean=false,
    val errorMessage:String?=null

)

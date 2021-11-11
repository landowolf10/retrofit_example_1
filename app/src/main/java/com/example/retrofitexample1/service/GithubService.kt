package com.example.retrofitexample1.service


import com.example.retrofitexample1.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService
{
    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user:String?): Call<MutableList<Repo>>
}
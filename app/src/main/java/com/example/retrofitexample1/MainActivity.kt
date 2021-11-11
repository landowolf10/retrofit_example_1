package com.example.retrofitexample1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexample1.adapter.RecyclerAdapter
import com.example.retrofitexample1.model.Repo
import com.example.retrofitexample1.service.GithubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var itemAdapter: RecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemAdapter = RecyclerAdapter()
        initRecycler(itemAdapter)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GithubService::class.java)
        val repos = service.listRepos("landowolf10")

        repos.enqueue(object: Callback<MutableList<Repo>>
        {
            override fun onFailure(call: Call<MutableList<Repo>>, t: Throwable) {
                call.cancel()
            }

            override fun onResponse(call: Call<MutableList<Repo>>, response: Response<MutableList<Repo>>) {
                if(response.isSuccessful)
                {
                    print(response.body())

                    response.body()?.let {
                            repos -> (recyclerView.adapter as RecyclerAdapter).setNamesList(repos)
                    }
                }
                else
                    Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_LONG).
                        show()
            }
        })
    }

    private fun initRecycler(noteAdapter:RecyclerAdapter)
    {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = noteAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
package com.example.starwars

import android.app.Application
import com.example.starwars.network.ApiService
import com.example.starwars.network.RetrofitInstance
import com.example.starwars.repository.StarwarsRepository

class StarWarsApplication : Application() {
    lateinit var repository: StarwarsRepository
    private lateinit var apiService: ApiService
    override fun onCreate() {
        super.onCreate()
        apiService = RetrofitInstance.apiService
        repository = StarwarsRepository(apiService)    }
}
package com.example.starwars.repository

import com.example.starwars.model.apimodel.MatchList
import com.example.starwars.model.apimodel.PlayerList
import com.example.starwars.network.ApiService

class StarwarsRepository(private val apiService: ApiService) {

    suspend fun getPlayerList() : PlayerList {
        return apiService.getPlayerList().body()!!
    }

    suspend fun getMatchList() : MatchList {
        return apiService.getMatchList().body()!!
    }

}
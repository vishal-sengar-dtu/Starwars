package com.example.starwars.network

import com.example.starwars.model.apimodel.MatchList
import com.example.starwars.model.apimodel.PlayerList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("b/IKQQ")
    suspend fun getPlayerList() : Response<PlayerList>

    @GET("b/JNYL")
    suspend fun getMatchList() : Response<MatchList>
}
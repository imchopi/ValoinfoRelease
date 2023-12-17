package com.example.valoinfo.data.api

import com.example.valoinfo.data.db.agents.Agent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface ValorantApi {
    @GET("agents")
    suspend fun getAgents(@Query("limit") limit:Int=20, @Query("offset") offset:Int=0): AgentData

    @GET("agents/{uuid}")
    suspend fun getAgentsByUuid(@Query("limit") limit:Int=20, @Query("offset") offset:Int=0): Agent
}

@Singleton
class ValorantService @Inject constructor() {
    private val apiUrl = Retrofit.Builder()
        .baseUrl("https://valorant-api.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: ValorantApi = apiUrl.create(ValorantApi::class.java)
}

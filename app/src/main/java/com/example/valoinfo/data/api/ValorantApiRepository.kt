package com.example.valoinfo.data.api

import android.util.Log
import com.example.valoinfo.data.db.agents.Agent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValorantApiRepository @Inject constructor(private val service: ValorantService) {

    suspend fun getAgents(): List<Agent> {
        try {
            val simpleList = service.api.getAgents(300,0)
            val agent = simpleList.data.filter { agent ->
                agent.isPlayableCharacter
            }.map { valorantListItem ->
                Agent(
                    valorantListItem.uuid,
                    valorantListItem.displayName,
                    valorantListItem.description,
                    valorantListItem.displayIcon,
                    valorantListItem.isPlayableCharacter,
                )
            }
            return agent
        } catch (e: Exception) {
            return emptyList()
        }
    }
}

package com.example.valoinfo.data.repository

import com.example.valoinfo.data.api.ValorantApiRepository
import com.example.valoinfo.data.api.asEntityModel
import com.example.valoinfo.data.db.agents.Agent
import com.example.valoinfo.data.db.agents.AgentsDBRepository
import com.example.valoinfo.data.db.agents.asAgent
import com.example.valoinfo.data.db.agentsTierlist.AgentsTierlistDBRepository
import com.example.valoinfo.data.db.agentsTierlist.TierlistWithAgents
import com.example.valoinfo.data.db.tierlist.Tierlist
import com.example.valoinfo.data.db.tierlist.TierlistDBRepository
import com.example.valoinfo.data.db.tierlist.TierlistEntity
import com.example.valoinfo.data.db.tierlist.asTierlist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValorantRepository @Inject constructor(
    private val agentsDBRepository: AgentsDBRepository,
    private val tierlistDBRepository: TierlistDBRepository,
    private val agentsTierlistDBRepository: AgentsTierlistDBRepository,
    private val apiRepository: ValorantApiRepository,
) {
    val agent: Flow<List<Agent>>
        get() {
            val list = agentsDBRepository.agents.map {
                it.asAgent()
            }
            return list
        }

    val tierlist: Flow<List<Tierlist>>
        get() {
            val tierlist = tierlistDBRepository.tierlist.map {
                it.asTierlist()
            }
            return tierlist
        }

    suspend fun refreshList() {
        withContext(Dispatchers.IO) {
            val apiValorant = apiRepository.getAgents()
            agentsDBRepository.insert(apiValorant.asEntityModel())
        }
    }

    suspend fun createTierlist(displayName: String, agents: List<String>) {
        withContext(Dispatchers.IO) {
            agentsTierlistDBRepository.createTierlistAgents(displayName, agents)
        }
    }

    suspend fun getAgent(displayName: String) = agentsDBRepository.getAgentByDisplayName(displayName)
    fun getTierlistWithAgents(id: Long): Flow<TierlistWithAgents> {
        return agentsTierlistDBRepository.getTierlistWithAgents(id)
    }


}
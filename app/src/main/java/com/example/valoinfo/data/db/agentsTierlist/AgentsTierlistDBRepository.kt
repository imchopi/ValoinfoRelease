package com.example.valoinfo.data.db.agentsTierlist

import androidx.annotation.WorkerThread
import com.example.valoinfo.data.db.tierlist.TierlistDao
import com.example.valoinfo.data.db.tierlist.TierlistEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AgentsTierlistDBRepository @Inject constructor(
    private val agentsTierlistDao: AgentsTierlistDao,
    private val tierlistDao: TierlistDao

) {
    val agentsTierlist: Flow<List<AgentsTierlistEntity>> = agentsTierlistDao.getAgentsTierlist()

    @WorkerThread
    suspend fun insert(listAgentsTierlistDaoEntity: List<AgentsTierlistEntity>) {
        agentsTierlistDao.insert(listAgentsTierlistDaoEntity)
    }

    @WorkerThread
    fun getAgentByDisplayName(uuid: String): Flow<AgentsTierlistEntity> {
        return agentsTierlistDao.getAgentInTierlistByDisplayName(uuid)
    }

    @WorkerThread
    fun getTierlistWithAgents(uuid: Long): Flow<TierlistWithAgents> {
        return agentsTierlistDao.getTierlistWithAgents(uuid)
    }

    @WorkerThread
    suspend fun createTierlistAgents(tierlistName: String, checkedAgent: List<String>){
        val tierlist = TierlistEntity(
            0,
            tierlistName
        )
        val tierlistId = tierlistDao.insert(tierlist)
        val tierlistAgent = checkedAgent.map { agentId ->
            AgentsTierlistEntity(
                tierlistId,
                agentId,
            )
        }
        agentsTierlistDao.insert(tierlistAgent)
    }

}
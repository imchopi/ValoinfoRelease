package com.example.valoinfo.data.db.agents

import androidx.annotation.WorkerThread
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AgentsDBRepository @Inject constructor(private val agentsDao: AgentsDao) {
    val agents: Flow<List<AgentsEntity>> = agentsDao.getAgents()

    @WorkerThread
    suspend fun insert(listAgentEntity: List<AgentsEntity>) {
        agentsDao.insert(listAgentEntity)
    }

    @WorkerThread
    suspend fun getAgentByDisplayName(displayName: String): Flow<AgentsEntity> {
        return agentsDao.getAgentByDisplayName(displayName)
    }

}
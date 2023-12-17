package com.example.valoinfo.data.db.agents

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listAgentsEntity: List<AgentsEntity>)

    @Query("SELECT * FROM agents")
    fun getAgents(): Flow<List<AgentsEntity>>

    @Query("SELECT * FROM agents WHERE displayName= :displayName")
    fun getAgentByDisplayName(displayName: String): Flow<AgentsEntity>
}

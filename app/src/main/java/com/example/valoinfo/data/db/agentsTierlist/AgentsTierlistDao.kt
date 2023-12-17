package com.example.valoinfo.data.db.agentsTierlist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.valoinfo.data.db.agents.AgentsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentsTierlistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listAgentTierlistEntity: List<AgentsTierlistEntity>)

    @Query("SELECT * FROM agents_tierlist")
    fun getAgentsTierlist(): Flow<List<AgentsTierlistEntity>>

    @Query("SELECT * FROM agents_tierlist WHERE id = :tierlistId")
    fun getAgentInTierlistByDisplayName(tierlistId: String): Flow<AgentsTierlistEntity>

    @Query("SELECT * FROM tierlist WHERE id = :tierlistId")
    fun getTierlistWithAgents(tierlistId: Long): Flow<TierlistWithAgents>
}
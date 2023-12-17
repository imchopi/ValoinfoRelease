package com.example.valoinfo.data.api

import com.example.valoinfo.data.db.agents.Agent
import com.example.valoinfo.data.db.agents.AgentsEntity

data class AgentData(
    val status: Int,
    val data: List<Agent>
)

fun List<Agent>.asEntityModel(): List<AgentsEntity> {
    return this.map {
       AgentsEntity(
            it.uuid,
            it.displayName,
            it.description,
            it.displayIcon,
            it.isPlayableCharacter
        )
    }
}

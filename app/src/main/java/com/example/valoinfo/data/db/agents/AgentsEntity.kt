package com.example.valoinfo.data.db.agents

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "agents")
data class AgentsEntity (
    @PrimaryKey
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val isPlayableCharacter: Boolean
)

fun List<AgentsEntity>.asAgent():List<Agent> {
    return this.map {
        Agent(
            it.uuid,
            it.displayName,
            it.description,
            it.displayIcon,
            it.isPlayableCharacter
        )
    }
}
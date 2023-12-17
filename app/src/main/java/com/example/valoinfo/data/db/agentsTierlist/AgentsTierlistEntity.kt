package com.example.valoinfo.data.db.agentsTierlist

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.valoinfo.data.db.agents.Agent
import com.example.valoinfo.data.db.agents.AgentsEntity
import com.example.valoinfo.data.db.tierlist.Tierlist
import com.example.valoinfo.data.db.tierlist.TierlistEntity

/*@Entity(tableName = "agents_tierlist",
    foreignKeys = [
        ForeignKey(
            entity = TierlistEntity::class,
            parentColumns = ["id"],
            childColumns = ["tierlistId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AgentsEntity::class,
            parentColumns = ["uuid"],
            childColumns = ["agentId"],
        )
    ],
)*/
@Entity(
    tableName = "agents_tierlist",
    primaryKeys = [
        "id", "uuid"
    ]
)
data class AgentsTierlistEntity(
    val id: Long,
    val uuid: String
)

data class TierlistWithAgents(
    @Embedded val tierlist: Tierlist,
    @Relation(
        parentColumn = "id",
        entityColumn = "uuid",
        associateBy = Junction(AgentsTierlistEntity::class)
    )
    val agents: List<AgentsEntity>
)

fun List<AgentsTierlistEntity>.asAgentTierlist():List<AgentsTierlist> {
    return this.map {
        AgentsTierlist(
            it.uuid,
            it.id,
        )
    }
}
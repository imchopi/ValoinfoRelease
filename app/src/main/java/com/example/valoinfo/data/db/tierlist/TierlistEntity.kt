package com.example.valoinfo.data.db.tierlist

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tierlist")
data class TierlistEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val displayName: String,
)

fun List<TierlistEntity>.asTierlist():List<Tierlist> {
    return this.map {
        Tierlist(
            it.id,
            it.displayName,
        )
    }
}
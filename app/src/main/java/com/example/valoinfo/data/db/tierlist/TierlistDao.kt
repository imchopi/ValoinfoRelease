package com.example.valoinfo.data.db.tierlist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TierlistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listTierlistEntity: TierlistEntity): Long

    @Query("SELECT * FROM tierlist")
    fun getTierlist(): Flow<List<TierlistEntity>>

    @Query("SELECT * FROM tierlist WHERE displayName= :displayName")
    fun getTierlistByDisplayName(displayName: String): Flow<TierlistEntity>

}
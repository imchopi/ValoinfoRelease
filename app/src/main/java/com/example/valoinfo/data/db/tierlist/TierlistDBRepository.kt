package com.example.valoinfo.data.db.tierlist

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TierlistDBRepository @Inject constructor(private val tierlistDao: TierlistDao) {
    val tierlist: Flow<List<TierlistEntity>> = tierlistDao.getTierlist()

    @WorkerThread
    suspend fun insert(listTierlistEntity: TierlistEntity) {
        tierlistDao.insert(listTierlistEntity)
    }

    @WorkerThread
    suspend fun getAgentByDisplayName(uuid: String): Flow<TierlistEntity> {
        return tierlistDao.getTierlistByDisplayName(uuid)
    }

}
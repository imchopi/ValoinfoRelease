package com.example.valoinfo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.valoinfo.data.db.agents.AgentsDao
import com.example.valoinfo.data.db.agents.AgentsEntity
import com.example.valoinfo.data.db.agentsTierlist.AgentsTierlistDao
import com.example.valoinfo.data.db.agentsTierlist.AgentsTierlistEntity
import com.example.valoinfo.data.db.tierlist.TierlistDao
import com.example.valoinfo.data.db.tierlist.TierlistEntity

@Database(entities = [AgentsEntity::class, TierlistEntity::class, AgentsTierlistEntity::class], version = 1)
abstract class ValorantDatabase(): RoomDatabase() {
    abstract fun agentsDao(): AgentsDao
    abstract fun tierlistDao(): TierlistDao
    abstract fun agentsTierlist(): AgentsTierlistDao

    companion object {
        @Volatile
        private var _INSTANCE: ValorantDatabase? = null

        fun getInstance(context: Context): ValorantDatabase {
            return _INSTANCE ?: synchronized(this) {
                _INSTANCE ?: buildDatabase(context).also {
                    _INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context): ValorantDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ValorantDatabase::class.java,
                "valorant_db"
            ).build()
        }
    }
}
package com.example.valoinfo.di

import android.content.Context
import com.example.valoinfo.data.db.agents.AgentsDao
import com.example.valoinfo.data.db.ValorantDatabase
import com.example.valoinfo.data.db.agentsTierlist.AgentsTierlistDao
import com.example.valoinfo.data.db.tierlist.TierlistDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideValorantDatabase(@ApplicationContext context: Context): ValorantDatabase {
        return ValorantDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideAgentsDao(database: ValorantDatabase): AgentsDao {
        return database.agentsDao()
    }

    @Singleton
    @Provides
    fun provideTierlistDao(database: ValorantDatabase): TierlistDao {
        return database.tierlistDao()
    }

    @Singleton
    @Provides
    fun provideAgentsTierlistDao(database: ValorantDatabase): AgentsTierlistDao {
        return database.agentsTierlist()
    }

}
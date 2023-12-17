package com.example.valoinfo.ui.allTier

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valoinfo.data.db.agents.Agent
import com.example.valoinfo.data.db.agents.asAgent
import com.example.valoinfo.data.repository.ValorantRepository
import com.example.valoinfo.ui.detail.DetailUiState
import com.example.valoinfo.ui.tierlist.TierlistUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllTierViewModel @Inject constructor(private val repository: ValorantRepository): ViewModel(){
    private val _allTier = MutableStateFlow(AllTierUiState(listOf()))
    val allTier: StateFlow<AllTierUiState>
        get() = _allTier.asStateFlow()

    fun getAgent (id: Long){
        viewModelScope.launch {
            repository.getTierlistWithAgents(id).collect { agent ->
                val agents = agent.agents.asAgent()
                _allTier.value = AllTierUiState(agents)
            }
        }
    }

}

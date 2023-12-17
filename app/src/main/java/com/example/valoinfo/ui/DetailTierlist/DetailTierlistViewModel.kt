package com.example.valoinfo.ui.DetailTierlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valoinfo.data.repository.ValorantRepository
import com.example.valoinfo.ui.agent.AgentUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTierlistViewModel @Inject constructor(private val repository: ValorantRepository): ViewModel() {
    private val _tierlistDetail = MutableStateFlow(AgentUiState(listOf()))
    val tierlistDetail: StateFlow<AgentUiState>
        get() = _tierlistDetail.asStateFlow()

    fun createTierlist (displayName: String, agents: List<String>){
        viewModelScope.launch {
            repository.createTierlist(displayName, agents)
        }
    }

    init {
        viewModelScope.launch {
            repository.agent.collect {
                _tierlistDetail.value = AgentUiState(it)
            }
        }
    }
}
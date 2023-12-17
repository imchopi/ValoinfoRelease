package com.example.valoinfo.ui.agent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valoinfo.data.repository.ValorantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AgentViewModel @Inject constructor(private val repository: ValorantRepository): ViewModel() {
    private val _uiState = MutableStateFlow(AgentUiState(listOf()))
    val uiState: StateFlow<AgentUiState>
        get() = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                repository.refreshList()
            }
            catch (e:IOException) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message)
            }
        }

        viewModelScope.launch {
            repository.agent.collect() {
                _uiState.value = AgentUiState(it)
            }
        }
    }
}
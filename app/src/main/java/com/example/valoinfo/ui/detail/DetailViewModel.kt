package com.example.valoinfo.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valoinfo.data.repository.ValorantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: ValorantRepository): ViewModel() {
    private val _skill = MutableStateFlow(DetailUiState())
    val skill: StateFlow<DetailUiState>
        get() = _skill.asStateFlow()

   fun getAgent (uuid: String){
        viewModelScope.launch {
            repository.getAgent(uuid).collect{ agent ->
                    _skill.value = DetailUiState(
                        agent.uuid,
                        agent.displayName,
                        agent.displayIcon
                    )
                }
            }
        }
    }

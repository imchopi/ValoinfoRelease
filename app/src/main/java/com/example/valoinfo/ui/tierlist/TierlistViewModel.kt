package com.example.valoinfo.ui.tierlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valoinfo.data.repository.ValorantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class TierlistViewModel @Inject constructor(private val repository: ValorantRepository): ViewModel() {
    private val _tierlistUiState = MutableStateFlow(TierlistUiState(listOf()))
    val tierlistUiState: StateFlow<TierlistUiState>
        get() = _tierlistUiState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                repository.tierlist.collect() {
                    _tierlistUiState.value = TierlistUiState(it)
                }
            }
            catch (e: IOException) {
                _tierlistUiState.value = _tierlistUiState.value.copy(errorMessage = e.message)
            }
        }

    }

}

package com.ucne.gastos.ui.gastosList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.gastos.data.remote.dto.GastoDto
import com.ucne.gastos.data.remote.repository.GastoRepository
import com.ucne.gastos.utils.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

data class GastosListUiState(
    val isLoading : Boolean = false,
    val gastos: List<GastoDto> = emptyList(),
    val error : String = ""
)

@HiltViewModel
class GastosListViewModel @Inject constructor(
    repository: GastoRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(GastosListUiState())
    val uiState: StateFlow<GastosListUiState> = _uiState.asStateFlow()

    var id by mutableStateOf(0)

    init {
        repository.getListGastos().onEach { result ->
            when (result) {
                is Resources.Loading -> {
                    _uiState.value = GastosListUiState(isLoading = true)
                }

                is Resources.Success -> {
                    _uiState.value = GastosListUiState(gastos = result.data ?: emptyList())
                }

                is Resources.Error -> {
                    _uiState.value = GastosListUiState(error = result.message ?: "Error")
                }
            }
        }.launchIn(viewModelScope)
    }
}
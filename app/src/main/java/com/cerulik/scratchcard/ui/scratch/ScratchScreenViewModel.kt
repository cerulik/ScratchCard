package com.cerulik.scratchcard.ui.scratch

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerulik.scratchcard.domain.GetScratchCardStateUseCase
import com.cerulik.scratchcard.domain.ScratchCardUseCase
import kotlinx.coroutines.launch

class ScratchScreenViewModel(
    private val getScratchCardUseCase: GetScratchCardStateUseCase,
    private val scratchCardUseCase: ScratchCardUseCase
) : ViewModel() {

    private val _viewState = mutableStateOf<ScratchScreenState>(ScratchScreenState.Loading)
    val viewState: MutableState<ScratchScreenState> = _viewState

    init {
        viewModelScope.launch {
            getScratchCardUseCase.invoke().collect {
                _viewState.value = ScratchScreenState.Loaded(it)
            }
        }
    }

    fun scratchCard() {
        viewModelScope.launch {
            _viewState.value = ScratchScreenState.Loading
            scratchCardUseCase.invoke()
        }
    }
}
package com.cerulik.scratchcard.ui.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerulik.scratchcard.domain.GetScratchCardStateUseCase
import com.cerulik.scratchcard.domain.model.ScratchCardState
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val getScratchCardUseCase: GetScratchCardStateUseCase
) : ViewModel() {

    private val _homeCardState = mutableStateOf<ScratchCardState>(ScratchCardState.Unscratched)
    val homeScreenState: MutableState<ScratchCardState> = _homeCardState

    init {
        viewModelScope.launch {
            getScratchCardUseCase.invoke().collect {
                _homeCardState.value = it
            }
        }
    }
}
package com.cerulik.scratchcard.ui.activation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cerulik.scratchcard.domain.ActivateCardUseCase
import com.cerulik.scratchcard.domain.GetScratchCardStateUseCase
import com.cerulik.scratchcard.domain.model.ScratchCardState
import kotlinx.coroutines.launch

class ActivationScreenViewModel(
    private val getScratchCardUseCase: GetScratchCardStateUseCase,
    private val activateCardUseCase: ActivateCardUseCase
) : ViewModel() {

    private val _viewState = mutableStateOf<ActivationScreenState>(ActivationScreenState.Loading)
    val viewState: MutableState<ActivationScreenState> = _viewState

    init {
        viewModelScope.launch {
            getScratchCardUseCase.invoke().collect {
                _viewState.value = ActivationScreenState.Loaded(it)
            }
        }
    }

    fun activateCard() {
        viewModelScope.launch {
            val activationScreenState = _viewState.value
            if (activationScreenState is ActivationScreenState.Loaded) {
                if (activationScreenState.scratchCardState is ScratchCardState.Scratched) {
                    _viewState.value = ActivationScreenState.Loading
                    activateCardUseCase.invoke(
                        activationScreenState.scratchCardState.code
                    ).onFailure {
                        _viewState.value = ActivationScreenState.Loaded(
                            activationScreenState.scratchCardState,
                            it.message
                        )
                    }
                }
            }
        }
    }
}
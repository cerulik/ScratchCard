package com.cerulik.scratchcard.ui.activation

import com.cerulik.scratchcard.domain.model.ScratchCardState

sealed class ActivationScreenState {
    data object Loading : ActivationScreenState()
    data class Loaded(
        val scratchCardState: ScratchCardState,
        val errorMessage: String? = null
    ) : ActivationScreenState()
}

package com.cerulik.scratchcard.ui.scratch

import com.cerulik.scratchcard.domain.model.ScratchCardState

sealed class ScratchScreenState {
    data object Loading : ScratchScreenState()
    data class Loaded(val scratchCardState: ScratchCardState) : ScratchScreenState()
}

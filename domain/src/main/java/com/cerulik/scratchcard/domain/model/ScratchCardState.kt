package com.cerulik.scratchcard.domain.model

sealed class ScratchCardState {
    object Unscratched : ScratchCardState()
    data class Scratched(
        val code: String,
        val isActivating: Boolean = false
    ) : ScratchCardState()
    data class Activated(val code: String) : ScratchCardState()
}
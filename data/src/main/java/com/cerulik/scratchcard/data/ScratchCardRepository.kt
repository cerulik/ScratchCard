package com.cerulik.scratchcard.data

import com.cerulik.scratchcard.data.model.ScratchCardState
import kotlinx.coroutines.flow.StateFlow

interface ScratchCardRepository {
    fun getScratchCardState(): StateFlow<ScratchCardState>
    suspend fun scratchCard(): Result<String>
    suspend fun activateCard(code: String): Result<Boolean>
}
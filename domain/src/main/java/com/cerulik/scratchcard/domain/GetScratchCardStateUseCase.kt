package com.cerulik.scratchcard.domain

import com.cerulik.scratchcard.data.ScratchCardRepository
import com.cerulik.scratchcard.domain.model.ScratchCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetScratchCardStateUseCase(private val scratchCardRepository: ScratchCardRepository) {
    operator fun invoke(): Flow<ScratchCardState> {
        return scratchCardRepository.getScratchCardState().map {
            when (it) {
                is com.cerulik.scratchcard.data.model.ScratchCardState.Unscratched ->
                    ScratchCardState.Unscratched

                is com.cerulik.scratchcard.data.model.ScratchCardState.Scratched ->
                    ScratchCardState.Scratched(it.code, it.isActivating)

                is com.cerulik.scratchcard.data.model.ScratchCardState.Activated ->
                    ScratchCardState.Activated(it.code)
            }
        }
    }
}
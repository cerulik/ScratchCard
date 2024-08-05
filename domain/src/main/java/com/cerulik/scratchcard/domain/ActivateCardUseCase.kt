package com.cerulik.scratchcard.domain

import com.cerulik.scratchcard.data.ScratchCardRepository

class ActivateCardUseCase(private val repository: ScratchCardRepository) {
    suspend operator fun invoke(code: String): Result<Boolean> {
        return repository.activateCard(code)
    }
}
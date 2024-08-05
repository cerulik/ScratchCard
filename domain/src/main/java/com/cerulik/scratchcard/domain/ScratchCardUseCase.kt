package com.cerulik.scratchcard.domain

import com.cerulik.scratchcard.data.ScratchCardRepository

class ScratchCardUseCase(private val repository: ScratchCardRepository) {
    suspend operator fun invoke(): Result<String> {
        return repository.scratchCard()
    }
}
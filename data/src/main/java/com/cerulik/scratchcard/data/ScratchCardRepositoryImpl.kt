package com.cerulik.scratchcard.data

import com.cerulik.scratchcard.data.model.ScratchCardState
import com.cerulik.scratchcard.data.remote.api.ScratchCardApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.util.UUID

class ScratchCardRepositoryImpl(
    private val scratchCardApi: ScratchCardApi,
    private val ioDispatcher: CoroutineDispatcher,
    private val applicationScope: CoroutineScope
) : ScratchCardRepository {
    private var scratchCardState: MutableStateFlow<ScratchCardState> =
        MutableStateFlow(ScratchCardState.Unscratched)

    override fun getScratchCardState(): StateFlow<ScratchCardState> {
        return scratchCardState
    }

    override suspend fun scratchCard(): Result<String> = withContext(ioDispatcher) {
        delay(2000)

        return@withContext if (scratchCardState.value is ScratchCardState.Unscratched) {
            val code = UUID.randomUUID().toString()
            scratchCardState.value = ScratchCardState.Scratched(code)
            Result.success(code)
        } else {
            Result.failure(ScratchCardAlreadyScratchedException())
        }
    }

    override suspend fun activateCard(code: String): Result<Boolean> {
        return applicationScope.async(ioDispatcher) {
            try {
                scratchCardState.value = ScratchCardState.Scratched(code, true)
                val response = scratchCardApi.activateCard(code)
                delay(5000)

                if (response.android.toInt() > 277028) {
                    scratchCardState.value = ScratchCardState.Activated(response.android)
                    Result.success(true)
                } else {
                    ScratchCardState.Scratched(code, false)
                    Result.failure(ActivationFailedException())
                }
            } catch (e: Exception) {
                ScratchCardState.Scratched(code, false)
                Result.failure(e)
            }
        }.await()
    }
}

class ScratchCardAlreadyScratchedException : Throwable()
class ActivationFailedException : Throwable()
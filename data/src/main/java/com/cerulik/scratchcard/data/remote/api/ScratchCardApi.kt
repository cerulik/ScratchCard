package com.cerulik.scratchcard.data.remote.api

import com.cerulik.scratchcard.data.remote.dto.ActivateScratchCardResp
import retrofit2.http.GET
import retrofit2.http.Query

interface ScratchCardApi {
    @GET("version")
    suspend fun activateCard(@Query("code") code: String): ActivateScratchCardResp
}
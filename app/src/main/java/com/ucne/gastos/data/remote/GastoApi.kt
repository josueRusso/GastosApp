package com.ucne.gastos.data.remote

import com.ucne.gastos.data.remote.dto.GastoDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GastoApi {

    @GET("Gastos")
    @Headers("X-API-key: test")
    suspend fun GetList(): List<GastoDto>

    @GET("Gastos/{id}")
    @Headers("X-API-key: test")
    suspend fun GetGasto(@Path("id") id: Int): GastoDto
}
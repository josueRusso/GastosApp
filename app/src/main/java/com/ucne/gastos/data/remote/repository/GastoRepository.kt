package com.ucne.gastos.data.remote.repository

import com.ucne.gastos.data.remote.GastoApi
import com.ucne.gastos.data.remote.dto.GastoDto
import com.ucne.gastos.utils.Resources
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class GastoRepository @Inject constructor(
    private val gasto : GastoApi
) {
    fun getListGastos(): Flow<Resources<List<GastoDto>>> = flow {
        try {
            emit(Resources.Loading())
            emit(Resources.Success(gasto.GetList()))
        } catch (e: IOException) {
            emit(Resources.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun getGastos(id: Int): GastoDto {
        try {
            return gasto.GetGasto(id)
        } catch (e: Exception) {
            throw e
        }
    }

}
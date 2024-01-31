package com.ucne.gastos.data.remote.dto

data class GastoDto(
    val idGasto: Int,
    val fecha: String,
    val idSuplidor: Int,
    val suplidor: String,
    val ncf: String,
    val concepto: String,
    val descuento: Double,
    val itbis: Int,
    val monto: Double,
)
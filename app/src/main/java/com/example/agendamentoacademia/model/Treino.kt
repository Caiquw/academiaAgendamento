package com.example.agendamentoacademia.model

import java.io.Serializable


data class Treino(
    val id: Int,
    val modalidade: String,
    val instrutor: String,
    val horario: String,
    val vagasTotais: Int,
    val vagasOcupadas: Int,
    val statusPresenca: StatusPresenca = StatusPresenca.NAO_MARCADO,
    val observacao: String? = null
) : Serializable {

    val vagasDisponiveis: Int
        get() = (vagasTotais - vagasOcupadas).coerceAtLeast(0)

    val estaLotado: Boolean
        get() = vagasDisponiveis <= 0

    companion object {
        private const val serialVersionUID: Long = 1L
    }
}

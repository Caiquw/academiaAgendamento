package com.example.agendamentoacademia.data

import com.example.agendamentoacademia.model.StatusPresenca
import com.example.agendamentoacademia.model.Treino


object TreinosMock {

    val lista: List<Treino> = listOf(
        Treino(
            id = 1,
            modalidade = "Muay Thai",
            instrutor = "Prof. Carlos Souza",
            horario = "Segunda, 19h00",
            vagasTotais = 15,
            vagasOcupadas = 12
        ),
        Treino(
            id = 2,
            modalidade = "Jiu-Jitsu",
            instrutor = "Prof. Ana Ribeiro",
            horario = "Terça, 20h00",
            vagasTotais = 10,
            vagasOcupadas = 10,
            observacao = "Turma avançada, faixa roxa ou superior"
        ),
        Treino(
            id = 3,
            modalidade = "Funcional",
            instrutor = "Prof. Lucas Lima",
            horario = "Quarta, 07h00",
            vagasTotais = 20,
            vagasOcupadas = 5
        ),
        Treino(
            id = 4,
            modalidade = "Boxe",
            instrutor = "Prof. Marina Alves",
            horario = "Quinta, 18h30",
            vagasTotais = 12,
            vagasOcupadas = 12,
            statusPresenca = StatusPresenca.LISTA_ESPERA
        ),
        Treino(
            id = 5,
            modalidade = "Yoga",
            instrutor = "Prof. Beatriz Nunes",
            horario = "Sexta, 08h00",
            vagasTotais = 18,
            vagasOcupadas = 9,
            observacao = "Trazer tapete próprio"
        ),
        Treino(
            id = 6,
            modalidade = "Crossfit",
            instrutor = "Prof. Rafael Costa",
            horario = "Sábado, 09h00",
            vagasTotais = 14,
            vagasOcupadas = 3,
            statusPresenca = StatusPresenca.CONFIRMADO
        )
    )
}

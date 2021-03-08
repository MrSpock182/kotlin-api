package com.example.alura.kotlin.domain.dto

import com.example.alura.kotlin.domain.enumerable.StatusTopicoEnum
import java.time.LocalDateTime

data class DetalhesDoTopicoDto(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val nomeAutor: String,
    val status: StatusTopicoEnum,
    val respostas: List<RespostaDto>,
)

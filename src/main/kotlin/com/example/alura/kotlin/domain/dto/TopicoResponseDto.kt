package com.example.alura.kotlin.domain.dto

import java.time.LocalDateTime

data class TopicoResponseDto(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime
)

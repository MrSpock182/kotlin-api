package com.example.alura.kotlin.service

import com.example.alura.kotlin.domain.dto.DetalhesDoTopicoDto
import com.example.alura.kotlin.domain.dto.TopicoAtualizacaoDto
import com.example.alura.kotlin.domain.dto.TopicoRequestDto
import com.example.alura.kotlin.domain.dto.TopicoResponseDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TopicoService {
    fun listar(nomeCurso: String?, paginacao: Pageable) : Page<TopicoResponseDto>

    fun cadastrar(request: TopicoRequestDto) : TopicoResponseDto

    fun detalhar(id: Long): DetalhesDoTopicoDto

    fun atualizar(request: TopicoAtualizacaoDto) : TopicoResponseDto

    fun remover(id: Long)
}
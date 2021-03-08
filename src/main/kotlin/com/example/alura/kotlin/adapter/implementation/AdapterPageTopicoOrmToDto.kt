package com.example.alura.kotlin.adapter.implementation

import com.example.alura.kotlin.adapter.Adapter
import com.example.alura.kotlin.domain.dto.TopicoResponseDto
import com.example.alura.kotlin.repository.orm.Topico
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class AdapterPageTopicoOrmToDto : Adapter<Page<Topico>, Page<TopicoResponseDto>> {
    override fun cast(t1: Page<Topico>): Page<TopicoResponseDto> {
        return t1.map { v ->
            TopicoResponseDto(
                id = v.id,
                titulo = v.titulo,
                mensagem = v.mensagem,
                dataCriacao = v.dataCriacao
            )
        }
    }
}
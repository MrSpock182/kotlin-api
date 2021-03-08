package com.example.alura.kotlin.adapter.implementation

import com.example.alura.kotlin.adapter.Adapter
import com.example.alura.kotlin.domain.dto.TopicoResponseDto
import com.example.alura.kotlin.repository.orm.Topico
import org.springframework.stereotype.Component

@Component
class AdapterTopicoOrmToDto : Adapter<Topico, TopicoResponseDto> {

    override fun cast(t1: Topico): TopicoResponseDto {
        return TopicoResponseDto(
            id = t1.id,
            titulo = t1.titulo,
            mensagem = t1.mensagem,
            dataCriacao = t1.dataCriacao
        )
    }
}
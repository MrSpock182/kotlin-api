package com.example.alura.kotlin.adapter.implementation

import com.example.alura.kotlin.adapter.Adapter
import com.example.alura.kotlin.domain.dto.TopicoRequestDto
import com.example.alura.kotlin.repository.CursoRepository
import com.example.alura.kotlin.repository.orm.Topico
import org.springframework.stereotype.Component

@Component
class AdapterTopicoDtoToOrm(private val repository: CursoRepository) : Adapter<TopicoRequestDto, Topico> {

    override fun cast(t1: TopicoRequestDto): Topico {
        return Topico(
            titulo = t1.titulo,
            mensagem = t1.mensagem,
            curso = repository.findByNome(t1.nomeCurso)
        )
    }

}
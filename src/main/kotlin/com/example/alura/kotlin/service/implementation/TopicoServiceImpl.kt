package com.example.alura.kotlin.service.implementation

import com.example.alura.kotlin.adapter.Adapter
import com.example.alura.kotlin.domain.dto.*
import com.example.alura.kotlin.exception.NotFoundException
import com.example.alura.kotlin.repository.TopicoRepository
import com.example.alura.kotlin.repository.orm.Curso
import com.example.alura.kotlin.repository.orm.Topico
import com.example.alura.kotlin.repository.orm.Usuario
import com.example.alura.kotlin.service.TopicoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class TopicoServiceImpl(
    private val topicoRepository: TopicoRepository,
    private val adapterOrm: Adapter<Topico, TopicoResponseDto>,
    private val adapterRequest: Adapter<TopicoRequestDto, Topico>,
    private val adapterPage: Adapter<Page<Topico>, Page<TopicoResponseDto>>,
    private val mensagemNotFound: String = "Nenhum tópico encontrado"
) : TopicoService {
    override fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoResponseDto> {
        return if (nomeCurso == null) {
            adapterPage.cast(topicoRepository.findAll(paginacao))
        } else {
            adapterPage.cast(topicoRepository.findByCursoNome(paginacao = paginacao, nomeCurso = nomeCurso))
        }
    }

    override fun cadastrar(request: TopicoRequestDto): TopicoResponseDto {
        val topico = topicoRepository.save(adapterRequest.cast(request))
        return adapterOrm.cast(topico)
    }

    override fun detalhar(id: Long): DetalhesDoTopicoDto {
        val optional: Optional<Topico> = topicoRepository.findById(id)

        if (optional.isEmpty) {
            throw NotFoundException(mensagemNotFound)
        }

        val value: Topico = optional.get()
        return DetalhesDoTopicoDto(
            id = value.id,
            titulo = value.titulo,
            mensagem = value.mensagem,
            dataCriacao = value.dataCriacao,
            nomeAutor = value.autor!!.nome,
            status = value.status,
            respostas = value.respostas.stream()
                .map { v -> RespostaDto(v.id, v.mensagem, v.dataCriacao, v.autor.nome) }
                .collect(Collectors.toList()))
    }

    override fun atualizar(request: TopicoAtualizacaoDto): TopicoResponseDto {
        val optional: Optional<Topico> = topicoRepository.findById(request.id)

        if (optional.isEmpty) {
            throw NotFoundException(mensagemNotFound)
        }

        val value = optional.get()
        val topico: Topico = topicoRepository.save(
            Topico(
                id = value.id,
                titulo = request.titulo,
                mensagem = request.mensagem,
                dataCriacao = value.dataCriacao,
                status = value.status,
                autor = value.autor,
                curso = value.curso,
                respostas = value.respostas
            )
        )
        return adapterOrm.cast(topico)
    }

    override fun remover(id: Long) {
        val optional: Optional<Topico> = topicoRepository.findById(id)

        if (optional.isEmpty) {
            throw NotFoundException(mensagemNotFound)
        }

        topicoRepository.deleteById(id)
    }
}
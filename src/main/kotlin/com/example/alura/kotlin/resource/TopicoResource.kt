package com.example.alura.kotlin.resource

import com.example.alura.kotlin.domain.dto.DetalhesDoTopicoDto
import com.example.alura.kotlin.domain.dto.TopicoAtualizacaoDto
import com.example.alura.kotlin.domain.dto.TopicoRequestDto
import com.example.alura.kotlin.domain.dto.TopicoResponseDto
import com.example.alura.kotlin.service.TopicoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/topicos"])
class TopicoResource(private val service: TopicoService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = ["listaDeTopicos"])
    fun listar(
        @RequestParam(required = false) nomeCurso: String?,
        @PageableDefault(sort = ["dataCriacao"], direction = Sort.Direction.DESC, page = 0, size = 10) paginacao: Pageable
    ): Page<TopicoResponseDto>? {
        return service.listar(nomeCurso, paginacao)
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = ["listaDeTopicos"], allEntries = true)
    fun cadastrar(@RequestBody @Valid request: TopicoRequestDto) : TopicoResponseDto {
        return service.cadastrar(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = ["/{id}"])
    fun detalhar(@PathVariable id: Long) : DetalhesDoTopicoDto {
        return service.detalhar(id)
    }

    @Transactional
    @PutMapping(value = ["/{id}"])
    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(value = ["listaDeTopicos"], allEntries = true)
    fun atualizar(@RequestBody @Valid request: TopicoAtualizacaoDto) : TopicoResponseDto {
        return service.atualizar(request)
    }

    @Transactional
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = ["/{id}"])
    @CacheEvict(value = ["listaDeTopicos"], allEntries = true)
    fun remover(@PathVariable id: Long) {
        return service.remover(id)
    }

}
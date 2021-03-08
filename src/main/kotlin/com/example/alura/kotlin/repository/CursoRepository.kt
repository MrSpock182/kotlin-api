package com.example.alura.kotlin.repository

import com.example.alura.kotlin.repository.orm.Curso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CursoRepository : JpaRepository<Curso, Long> {
    fun findByNome(nome: String) : Curso
}
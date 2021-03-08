package com.example.alura.kotlin.repository

import com.example.alura.kotlin.repository.orm.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByEmail(email: String?) : Optional<Usuario>
}
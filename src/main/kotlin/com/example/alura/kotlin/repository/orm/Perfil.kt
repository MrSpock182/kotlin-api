package com.example.alura.kotlin.repository.orm

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Perfil(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String
) : GrantedAuthority {
    override fun getAuthority(): String {
        return nome
    }
}

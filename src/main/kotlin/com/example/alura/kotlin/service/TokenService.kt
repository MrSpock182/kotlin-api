package com.example.alura.kotlin.service

import org.springframework.security.core.Authentication

interface TokenService {
    fun gerarToken(auth: Authentication) : String
    fun isTokenValido(token: String?) : Boolean
    fun getIdUsuario(token: String?) : Long
}
package com.example.alura.kotlin.service

import com.example.alura.kotlin.repository.orm.Usuario

interface TokenService {
    fun gerarToken(usuario: Usuario) : String
    fun isTokenValido(token: String?) : Boolean
    fun getIdUsuario(token: String?) : Long
}
package com.example.alura.kotlin.service.implementation

import com.example.alura.kotlin.repository.UsuarioRepository
import com.example.alura.kotlin.repository.orm.Usuario
import javassist.NotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutenticacaoServiceImpl(private val repository: UsuarioRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario: Optional<Usuario> = repository.findByEmail(username)
        if(usuario.isEmpty) {
            throw NotFoundException("Usuário não encontrado")
        }
        return usuario.get()
    }
}
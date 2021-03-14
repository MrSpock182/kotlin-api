package com.example.alura.kotlin.configuration

import com.example.alura.kotlin.repository.UsuarioRepository
import com.example.alura.kotlin.repository.orm.Usuario
import com.example.alura.kotlin.service.TokenService
import javassist.NotFoundException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AutenticacaoViaTokenFilter(
    private val tokenService: TokenService,
    private val repository: UsuarioRepository
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filter: FilterChain) {
        val token = recuperarToken(request)
        val valido: Boolean = tokenService.isTokenValido(token)

        if (valido) {
            autenticarCliente(token)
        }

        filter.doFilter(request, response)
    }

    private fun autenticarCliente(token: String?) {
        val idUsuario: Long = tokenService.getIdUsuario(token)
        val optional: Optional<Usuario> = repository.findById(idUsuario)

        if (optional.isEmpty) {
            throw NotFoundException("Usuário não encontrado")
        }

        val usuario: Usuario = optional.get()
        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
            usuario,
            null,
            usuario.authorities
        )
    }

    private fun recuperarToken(request: HttpServletRequest): String? {
        val token: String? = request.getHeader("Authorization")
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null
        }
        return token.substring(7, token.length)
    }

}
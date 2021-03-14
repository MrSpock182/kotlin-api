package com.example.alura.kotlin.resource

import com.example.alura.kotlin.domain.dto.LoginDto
import com.example.alura.kotlin.domain.dto.TokenDto
import com.example.alura.kotlin.exception.NotFoundException
import com.example.alura.kotlin.repository.UsuarioRepository
import com.example.alura.kotlin.repository.orm.Usuario
import com.example.alura.kotlin.service.TokenService
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import java.lang.Exception
import java.util.*
import javax.validation.Valid

@RestController
//@RequestMapping(value = ["/auth"])
//@Profile(value = ["prod", "test"])
class AutenticacaoResource(
    private val tokenService: TokenService,
    private val repository: UsuarioRepository
) {

    @PostMapping(value = ["/auth"])
    @ResponseStatus(HttpStatus.OK)
    fun save(@RequestBody @Valid login: LoginDto) : TokenDto {
        val usuario = Usuario(
            nome = login.nome,
            email = login.email,
            senha = login.senha,
        )
        return TokenDto(tokenService.gerarToken(usuario), "Bearer")
    }

}
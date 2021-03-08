package com.example.alura.kotlin.resource

import com.example.alura.kotlin.domain.dto.LoginDto
import com.example.alura.kotlin.domain.dto.TokenDto
import com.example.alura.kotlin.service.TokenService
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/auth"])
@Profile(value = ["prod", "test"])
class AutenticacaoResource(
    private val authentication: AuthenticationManager,
    private val tokenService: TokenService
) {

    @PostMapping(value = ["/"])
    @ResponseStatus(HttpStatus.OK)
    fun autenticar(@RequestBody @Valid login: LoginDto) : TokenDto {
        val token: String = tokenService.gerarToken(
            authentication.authenticate(
                UsernamePasswordAuthenticationToken(login.email, login.senha)))
        return TokenDto(token, "Bearer")
    }

}
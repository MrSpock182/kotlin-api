package com.example.alura.kotlin.service.implementation

import com.example.alura.kotlin.repository.orm.Usuario
import com.example.alura.kotlin.service.TokenService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Service
class TokenJwtServiceImpl(
    @Value(value = "\${alura.jwt.expiration}") private val expiration: String,
    @Value(value = "\${alura.jwt.secret}") private val secret: String
) : TokenService {

    override fun gerarToken(usuario: Usuario): String {
        val hoje = Date()
        val dataExpiracao = Date(hoje.time + expiration.toLong())

        return Jwts.builder()
            .setIssuer("API do Fórum da Alura")
            .setSubject(usuario.id.toString())
            .setIssuedAt(hoje)
            .setExpiration(dataExpiracao)
            .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.toByteArray()))
            .compact()
    }

    override fun isTokenValido(token: String?): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            false
        }
    }

    override fun getIdUsuario(token: String?): Long {
        return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).body.subject.toLong()
    }
}
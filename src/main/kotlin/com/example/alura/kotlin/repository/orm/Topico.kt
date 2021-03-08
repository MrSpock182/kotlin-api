package com.example.alura.kotlin.repository.orm

import com.example.alura.kotlin.domain.enumerable.StatusTopicoEnum
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @Enumerated(EnumType.STRING)
    val status: StatusTopicoEnum = StatusTopicoEnum.NAO_RESPONDIDO,
    @ManyToOne
    val autor: Usuario? = null,
    @ManyToOne
    val curso: Curso,
    @OneToMany(mappedBy = "topico")
    val respostas: List<Resposta> = ArrayList()
)

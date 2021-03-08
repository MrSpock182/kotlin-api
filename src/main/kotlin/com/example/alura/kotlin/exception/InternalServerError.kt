package com.example.alura.kotlin.exception

class InternalServerError : RuntimeException {
    constructor(message: String): super(message)
    constructor(message: String, ex: Exception): super(message, ex)
    constructor(ex: Exception): super(ex)
}
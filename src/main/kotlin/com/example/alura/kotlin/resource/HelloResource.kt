package com.example.alura.kotlin.resource

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloResource {
    @GetMapping(value = ["/"])
    fun hello(): String {
        return "Hello World!"
    }
}


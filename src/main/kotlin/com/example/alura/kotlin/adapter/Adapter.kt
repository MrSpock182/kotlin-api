package com.example.alura.kotlin.adapter

interface Adapter<T1, T2> {
    fun cast(t1: T1) : T2
}
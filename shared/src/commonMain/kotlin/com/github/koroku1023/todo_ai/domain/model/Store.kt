package com.github.koroku1023.todo_ai.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Store(
    val id: String,
    val name: String,
    val description: String
)

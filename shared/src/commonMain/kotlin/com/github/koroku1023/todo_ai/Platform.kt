package com.github.koroku1023.todo_ai

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
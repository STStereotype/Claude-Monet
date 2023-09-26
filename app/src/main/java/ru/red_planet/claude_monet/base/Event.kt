package ru.red_planet.claude_monet.base

interface Event<T> {
    fun obtainEvent(event: T)
}

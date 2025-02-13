package com.example.notes.domain.model

data class Note(
    val id: Long = 0,
    val title: String,
    val content: String,
    val dateModified: Long
    // Можно добавить теги, категории и т.п.
)

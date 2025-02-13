package com.example.notes.data.repository

import com.example.notes.domain.model.Note
import com.example.notes.noteparser.Parser
import java.io.File


class NoteRepository {

    companion object {
        fun getAllNotes(): List<Note> {
            val note1 = Note( // временная заметка 1
                id = 1,
                title = "Первая заметка",
                content = "Это содержимое первой заметки.",
                dateModified = System.currentTimeMillis()
            )
            val note2 = Note( // временная заметка 2
                id = 2,
                title = "Вторая заметка",
                content = "Краткое описание второй заметки.",
                dateModified = System.currentTimeMillis()
            )
            return listOf(note1, note2)
        }
    }

    fun saveNote(note: Note) {
        // Вызываем FileHandler или DB
    }
}

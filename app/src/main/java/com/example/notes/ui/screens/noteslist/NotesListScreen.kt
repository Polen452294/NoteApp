package com.example.notes.ui.screens.noteslist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notes.domain.model.Note
import com.example.notes.ui.theme.NotesTheme

@Composable
fun NotesListScreen(navController: NavController, notes: List<Note>) {
    NotesTheme(darkTheme = true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp)
            ) {
                Text(
                    text = "Список заметок",
                    color = MaterialTheme.colorScheme.onBackground,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .padding(bottom = 16.dp)

                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(notes) { note ->
                        NoteItem(note, onClick = { /* Заглушка для клика */ })
                    }
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, onClick: () -> Unit) { //отображение заметки
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = note.title,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = note.content.take(50) + "...",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// Предпросмотр экрана списка заметок
@Preview(showBackground = true)
@Composable
fun PreviewNotesListScreen() {
    val sampleNotes = listOf(
        Note(id = 1, title = "Первая заметка", content = "Это содержимое первой заметки", dateModified = 0L),
        Note(id = 2, title = "Вторая заметка", content = "Краткое описание второй заметки", dateModified = 0L),
        Note(id = 3, title = "Третья заметка", content = "Длинное содержимое третьей заметки, чтобы проверить перенос строк", dateModified = 0L)
    )

    NotesListScreen(navController = rememberNavController(), notes = sampleNotes)
}

// Предпросмотр одного элемента списка заметок
@Preview(showBackground = true)
@Composable
fun PreviewNoteItem() {
    NoteItem(
        note = Note(id = 1, title = "Тестовая заметка", content = "Пример содержимого", dateModified = 0L),
        onClick = {}
    )
}

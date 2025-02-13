package com.example.notes.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notes.data.repository.NoteRepository
import com.example.notes.ui.screens.noteslist.NotesListScreen
import com.example.notes.ui.screens.notereader.NoteReaderScreen
import com.example.notes.ui.screens.noteeditor.NoteEditorScreen
import com.example.notes.ui.screens.settings.SettingsScreen

@Composable
fun NotesApp() {
    val navController = rememberNavController()

    // NavHost описывает все «пути» (routes) и связывает их с отдельными экранами
    NavHost(
        navController = navController,
        startDestination = "notesList" // начальный экран
    ) {
        composable("notesList") {
            NotesListScreen(navController, NoteRepository.getAllNotes())
        }
        composable("noteReader") {
            NoteReaderScreen(navController)
        }
        composable("noteEditor") {
            NoteEditorScreen(navController)
        }
        composable("settings") {
            SettingsScreen(navController)
        }
    }
}

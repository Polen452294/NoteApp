package com.example.notes.ui.screens.noteslist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notes.ui.theme.NotesTheme

@Composable
fun NotesListScreen(navController: NavController) {
    // Состояние для текстового поля поиска
    var searchText by remember { mutableStateOf("") }

    // Список задач (заглушка для демонстрации)
    var dailyTasks by remember {
        mutableStateOf(
            listOf(
                DailyTask("Купить продукты"),
                DailyTask("Сделать зарядку"),
                DailyTask("Завершить отчёт"),
                DailyTask("Позвонить родителям")
            )
        )
    }

    /**
     * Обратите внимание, что здесь используем:
     *  - MaterialTheme.colorScheme.background – для заливки всего экрана
     */
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            // Верхняя панель: поиск + кнопка "Войти"
            TopBar(
                searchText = searchText,
                onSearchTextChange = { searchText = it },
                onLoginClick = {
                    // Обработка нажатия на "Войти"
                }
            )

            // Блок "Расписание"
            ScheduleBlock()

            // Блок "Цели и задачи на день"
            Text(
                text = "Цели и задачи на день",
                style = MaterialTheme.typography.titleMedium,
                /**
                 * Обычно для текста на цветном фоне берут `color = MaterialTheme.colorScheme.onBackground`
                 * либо другой подходящий цвет.
                 */
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 8.dp, top = 16.dp)
            )
            DailyTasksList(
                tasks = dailyTasks,
                onTaskCheckedChange = { task, isChecked ->
                    dailyTasks = dailyTasks.map {
                        if (it == task) it.copy(completed = isChecked) else it
                    }
                }
            )
        }
    }
}

/** Верхняя горизонтальная панель поиска (и при желании кнопки "Войти") */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onLoginClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        /**
         * Настраиваем цвета TextField через textFieldColors,
         * используя текущую colorScheme.
         */
        TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            label = {
                Text(
                    text = "Поиск",
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            modifier = Modifier.weight(1f)
        )
    }
}

/** Блок "Расписание" с 4 строками */
@Composable
fun ScheduleBlock() {
    Text(
        text = "Расписание",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.padding(bottom = 8.dp)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Column {
            repeat(4) { index ->
                Text(
                    text = "Пункт #${index + 1} из расписания",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}

/** Список целей и задач, которые можно отмечать выполненными */
@Composable
fun DailyTasksList(
    tasks: List<DailyTask>,
    onTaskCheckedChange: (DailyTask, Boolean) -> Unit
) {
    LazyColumn {
        items(tasks) { task ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                /**
                 * Настраиваем цвета для Checkbox, чтобы они вписывались в тему
                 */
                Checkbox(
                    checked = task.completed,
                    onCheckedChange = { isChecked -> onTaskCheckedChange(task, isChecked) },
                    colors = CheckboxDefaults.colors(
                        checkedColor = MaterialTheme.colorScheme.primary,
                        uncheckedColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        checkmarkColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

/** Вспомогательный класс для хранения информации о задаче */
data class DailyTask(
    val title: String,
    val completed: Boolean = false
)

/** ---------------------- Превью (Preview) ---------------------- */

/**
 * Предпросмотр экрана целиком.
 * Оборачиваем в NotesTheme, чтобы работала наша тема.
 */
@Preview(showBackground = true)
@Composable
fun PreviewNotesListScreen() {
    NotesTheme(darkTheme = true) {
        val navController = rememberNavController()
        NotesListScreen(navController)
    }
}

/** Предпросмотр верхней панели */
@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    NotesTheme(darkTheme = true) {
        TopBar(
            searchText = "",
            onSearchTextChange = {},
            onLoginClick = {}
        )
    }
}

/** Предпросмотр блока "Расписание" */
@Preview(showBackground = true)
@Composable
fun PreviewScheduleBlock() {
    NotesTheme(darkTheme = true) {
        ScheduleBlock()
    }
}

/** Предпросмотр списка целей и задач */
@Preview(showBackground = true)
@Composable
fun PreviewDailyTasksList() {
    val sampleTasks = listOf(
        DailyTask("Купить продукты"),
        DailyTask("Сделать зарядку"),
        DailyTask("Посмотреть Compose-туториал"),
    )

    NotesTheme(darkTheme = true) {
        DailyTasksList(
            tasks = sampleTasks,
            onTaskCheckedChange = { _, _ -> }
        )
    }
}

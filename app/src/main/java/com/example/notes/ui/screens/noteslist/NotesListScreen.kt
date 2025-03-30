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
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun NotesListScreen(navController: NavController) {
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

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally // Центрируем по горизонтали
        ) {
            ScheduleBlock(scheduleItems = listOf(
                "1 пара орг",
                "2 пара орг",
                "3 пара орг",
                "4 пара орг")
            )

            Text(
                text = "Цели и задачи на день",
                style = MaterialTheme.typography.titleMedium,
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

/** Панель поиска*/
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
        /*TextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onBackground, // Цвет текста в фокусе
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground, // Цвет текста без фокуса
                focusedIndicatorColor = MaterialTheme.colorScheme.surface, // Линия в фокусе
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surface, // Линия без фокуса
                focusedContainerColor = MaterialTheme.colorScheme.surface, // Фон в фокусе
                unfocusedContainerColor = MaterialTheme.colorScheme.surface // Фон без фокуса
            ),
            label = {
                Text(
                    text = "Поиск",
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            modifier = Modifier.weight(1f)
        )*/
    }
}

// Блок расписания
@Composable
fun ScheduleBlock(scheduleItems: List<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth() // Занимает всю ширину экрана
            .padding(16.dp), // Добавляем отступ для всего блока
        horizontalAlignment = Alignment.CenterHorizontally // Выравниваем по горизонтали
    ) {
        Text(
            text = "Расписание на день",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 16.sp, // Размер заголовка
                fontWeight = FontWeight.Bold
            ),
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 16.dp) // Отступ снизу
        )

        // Контейнер для всех пунктов расписания
        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f) // Элементы занимают 80% ширины
                .clip(RoundedCornerShape(8.dp)) // Скругление углов
                .background(MaterialTheme.colorScheme.surface) // Фон для всего списка
                .padding(16.dp) // Внутренние отступы
        ) {
            // Отображаем каждый элемент расписания
            scheduleItems.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth() // Элементы занимают всю ширину
                            .wrapContentHeight() // Автоматически подстраивает высоту под контент
                            .padding(vertical = 2.dp), // Добавляем отступ между пунктами
                        contentAlignment = Alignment.CenterStart // Выравнивание текста по левому краю
                    ) {
                        Text(
                            text = item,
                            fontSize = 15.sp, // Размер текста
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    // Линия, разделяющая пункты
                    if (index != scheduleItems.lastIndex) {
                        Divider(
                            color = MaterialTheme.colorScheme.onPrimary, // Цвет линии
                            thickness = 1.dp, // Толщина линии
                            modifier = Modifier.padding(vertical = 8.dp) // Отступы вокруг линии
                        )
                    }
                }
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

// ---------------------- Превью (Preview) ----------------------
@Preview(showBackground = true)
@Composable
fun PreviewNotesListScreen() {
    NotesTheme(darkTheme = true) {
        val navController = rememberNavController()
        NotesListScreen(navController)
    }
}

/** Предпросмотр верхней панели */
//@Preview(showBackground = true)
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
@Preview
@Composable
fun PreviewScheduleBlock() {
    NotesTheme(darkTheme = true) {
        ScheduleBlock(
            scheduleItems = listOf(
                "1 пара",
                "2 пара",
                "3 пара",
                "4 пара"
            )
        )
    }
}


/** Предпросмотр списка целей и задач */
@Preview
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

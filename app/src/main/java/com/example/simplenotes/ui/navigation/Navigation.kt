package com.example.simplenotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.simplenotes.ui.screen.NoteDetailScreen
import com.example.simplenotes.ui.screen.NoteEditScreen
import com.example.simplenotes.ui.screen.NoteListScreen

sealed class Screen(val route: String) {
    object NoteList : Screen("note_list")
    object NoteDetail : Screen("note_detail/{noteId}")
    object NoteEdit : Screen("note_edit/{noteId?}")
}

@Composable
fun AppNavHost(navController: NavHostController, startDestination: String = Screen.NoteList.route) {
    NavHost(navController, startDestination) {
        composable(Screen.NoteList.route) {
            NoteListScreen(onNoteClick = { noteId ->
                navController.navigate("${Screen.NoteDetail.route.replace("{noteId}", noteId.toString())}")
            }, onNewNoteClick = {
                navController.navigate(Screen.NoteEdit.route.replace("{noteId?}", ""))
            })
        }
        composable(
            route = Screen.NoteDetail.route,
            arguments = listOf(androidx.navigation.navArgument("noteId") { type = androidx.navigation.NavType.LongType })
        ) { backStackEntry ->
            val noteId = backStackEntry.getLong("noteId") ?: 0L
            NoteDetailScreen(noteId = noteId, onEditClick = {
                navController.navigate("${Screen.NoteEdit.route.replace("{noteId?}", noteId.toString())}")
            }, onDeleteClick = {
                navController.popBackStack()
            })
        }
        composable(
            route = Screen.NoteEdit.route,
            arguments = listOf(androidx.navigation.navArgument("noteId") { type = androidx.navigation.NavType.LongType; defaultValue = "0" })
        ) { backStackEntry ->
            val noteId = backStackEntry.getLong("noteId") ?: 0L
            NoteEditScreen(noteId = if (noteId == 0L) null else noteId, onSave = {
                navController.popBackStack()
            }, onCancel = {
                navController.popBackStack()
            })
        }
    }
}

@Composable
fun RememberNavController(): NavHostController = rememberNavController()
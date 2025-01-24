package com.example.myapplication.presentation.navigation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationModel(
    val route: String,
    val title: String,
    val icon: ImageVector? = null
) {
    data object MainList : BottomNavigationModel(
        route = "mainList",
        title = "Главная",
        icon = Icons.Default.Home
    )

    data object Detail : BottomNavigationModel(
        route = "detail",
        title = "Детали",
        icon = Icons.Default.Info
    )

    data object Account : BottomNavigationModel(
        route = "account",
        title = "Аккаунт",
        icon = Icons.Default.AccountCircle
    )
}
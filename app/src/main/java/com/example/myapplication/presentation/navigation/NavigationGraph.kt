package com.example.myapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.presentation.navigation.model.BottomNavigationModel
import com.example.myapplication.presentation.screen.CompanyDetailScreen
import com.example.myapplication.presentation.screen.CompanyListScreen
import com.example.myapplication.presentation.viewModel.CompanyAppViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    viewModel: CompanyAppViewModel
) {
    NavHost(
        navController,
        startDestination = BottomNavigationModel.MainList.route
    ) {
        composable(BottomNavigationModel.MainList.route) {
            CompanyListScreen(
                viewModel,
                onCompanyClick = {
                    navController.navigate(
                        route = BottomNavigationModel.Detail.route
                    )
                }
            )
        }
        composable(BottomNavigationModel.Detail.route) {
            CompanyDetailScreen(
                viewModel.company.value ?: viewModel.getDefaultCompany(),
                onClickBack = {
                    navController.navigate(
                        route = BottomNavigationModel.MainList.route
                    )
                }
            )
        }
    }
}
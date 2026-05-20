package com.example.navigation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class FoodScreen {
    Start,
    MainDish,
    Drink,
    Summary
}

@Composable
fun NavigationApp(
    orderViewModel: OrderViewModel = viewModel()
) {
    val navController = rememberNavController()
    val order by orderViewModel.orderState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = FoodScreen.Start.name
    ) {
        composable(route = FoodScreen.Start.name) {
            StartScreen(
                onStartClicked = {
                    navController.navigate(FoodScreen.MainDish.name)
                }
            )
        }

        composable(route = FoodScreen.MainDish.name) {
            MainDishScreen(
                onDishSelected = { name, price ->
                    orderViewModel.chooseMainDish(name, price)
                    navController.navigate(FoodScreen.Drink.name)
                },
                onCancelClicked = {
                    orderViewModel.clearOrder()
                    navController.popBackStack(FoodScreen.Start.name, inclusive = false)
                }
            )
        }

        composable(route = FoodScreen.Drink.name) {
            DrinkScreen(
                onDrinkSelected = { name, price ->
                    orderViewModel.chooseDrink(name, price)
                    navController.navigate(FoodScreen.Summary.name)
                },
                onBackClicked = {
                    navController.popBackStack()
                },
                onCancelClicked = {
                    orderViewModel.clearOrder()
                    navController.popBackStack(FoodScreen.Start.name, inclusive = false)
                }
            )
        }

        composable(route = FoodScreen.Summary.name) {
            SummaryScreen(
                order = order,
                onBackClicked = {
                    navController.popBackStack()
                },
                onFinishClicked = {
                    orderViewModel.clearOrder()
                    navController.popBackStack(FoodScreen.Start.name, inclusive = false)
                }
            )
        }
    }
}
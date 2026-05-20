package com.example.navigation.ui

import androidx.lifecycle.ViewModel
import com.example.navigation.model.FoodOrder
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel : ViewModel() {

    private val _orderState = MutableStateFlow(FoodOrder())
    val orderState: StateFlow<FoodOrder> = _orderState.asStateFlow()

    fun chooseMainDish(name: String, price: Int) {
        _orderState.update {
            it.copy(
                mainDish = name,
                totalPrice = price + drinkPrice(it.drink)
            )
        }
    }

    fun chooseDrink(name: String, price: Int) {
        _orderState.update {
            it.copy(
                drink = name,
                totalPrice = mainDishPrice(it.mainDish) + price
            )
        }
    }

    fun clearOrder() {
        _orderState.value = FoodOrder()
    }

    private fun mainDishPrice(name: String): Int {
        return when (name) {
            "Burger" -> 8
            "Pizza" -> 10
            "Pasta" -> 9
            else -> 0
        }
    }

    private fun drinkPrice(name: String): Int {
        return when (name) {
            "Water" -> 1
            "Cola" -> 2
            "Orange juice" -> 3
            else -> 0
        }
    }
}
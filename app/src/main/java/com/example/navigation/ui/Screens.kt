package com.example.navigation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.navigation.model.FoodOrder

@Composable
fun StartScreen(
    onStartClicked: () -> Unit
) {
    ScreenContainer {
        Text(
            text = "Food Order App",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Create a simple food order using different screens.",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = onStartClicked) {
            Text("Start order")
        }
    }
}

@Composable
fun MainDishScreen(
    onDishSelected: (String, Int) -> Unit,
    onCancelClicked: () -> Unit
) {
    ScreenContainer {
        Text(
            text = "Choose your main dish",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        MenuOption(
            title = "Burger",
            price = 8,
            onClick = { onDishSelected("Burger", 8) }
        )

        MenuOption(
            title = "Pizza",
            price = 10,
            onClick = { onDishSelected("Pizza", 10) }
        )

        MenuOption(
            title = "Pasta",
            price = 9,
            onClick = { onDishSelected("Pasta", 9) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(onClick = onCancelClicked) {
            Text("Cancel")
        }
    }
}

@Composable
fun DrinkScreen(
    onDrinkSelected: (String, Int) -> Unit,
    onBackClicked: () -> Unit,
    onCancelClicked: () -> Unit
) {
    ScreenContainer {
        Text(
            text = "Choose your drink",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        MenuOption(
            title = "Water",
            price = 1,
            onClick = { onDrinkSelected("Water", 1) }
        )

        MenuOption(
            title = "Cola",
            price = 2,
            onClick = { onDrinkSelected("Cola", 2) }
        )

        MenuOption(
            title = "Orange juice",
            price = 3,
            onClick = { onDrinkSelected("Orange juice", 3) }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(onClick = onBackClicked) {
                Text("Back")
            }

            OutlinedButton(onClick = onCancelClicked) {
                Text("Cancel")
            }
        }
    }
}

@Composable
fun SummaryScreen(
    order: FoodOrder,
    onBackClicked: () -> Unit,
    onFinishClicked: () -> Unit
) {
    ScreenContainer {
        Text(
            text = "Order summary",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        SummaryRow(label = "Main dish", value = order.mainDish)
        SummaryRow(label = "Drink", value = order.drink)
        SummaryRow(label = "Total", value = "$${order.totalPrice}")

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(onClick = onBackClicked) {
                Text("Back")
            }

            Button(onClick = onFinishClicked) {
                Text("Finish")
            }
        }
    }
}

@Composable
private fun MenuOption(
    title: String,
    price: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "$$price",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun SummaryRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ScreenContainer(
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        content()
    }
}
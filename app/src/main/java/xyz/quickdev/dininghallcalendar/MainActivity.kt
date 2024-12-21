package xyz.quickdev.dininghallcalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import xyz.quickdev.dininghallcalendar.ui.components.DiningZone
import xyz.quickdev.dininghallcalendar.ui.theme.DiningHallCalendarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiningHallCalendarTheme {
                Column(modifier = Modifier.fillMaxSize()) {

                    val grillFood = listOf(
                        FoodItem(
                            "Bacon",
                            listOf(
                                NutritionLabel.Dairy,
                                NutritionLabel.Eggs,
                                NutritionLabel.Fish,
                                NutritionLabel.Soy
                            )
                        ),
                        FoodItem(
                            "Pancakes Plain",
                            listOf(
                                NutritionLabel.Eggs,
                                NutritionLabel.Vegetarian,
                            )
                        ),
                        FoodItem(
                            "Grilled Chicken Thigh",
                            listOf(
                                NutritionLabel.Halal,
                                NutritionLabel.Local,
                                NutritionLabel.Smart,
                            )
                        ),
                    )

                    DiningZone("Grill Works Sides", grillFood)
                    DiningZone("Waffle, Doughnut, Bagel Bar", emptyList())

                    NutritionLabel.entries.forEach {
                        it.Icon()
                    }
                }
            }
        }
    }
}
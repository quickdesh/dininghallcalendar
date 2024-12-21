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

                    DiningZone("Grill Works Sides")
                    DiningZone("Waffle, Doughnut, Bagel Bar")

                    NutritionLabel.entries.forEach {
                        it.Icon()
                    }
                }
            }
        }
    }
}
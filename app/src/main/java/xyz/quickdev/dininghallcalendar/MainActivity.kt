package xyz.quickdev.dininghallcalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.ui.Modifier
import xyz.quickdev.dininghallcalendar.ui.DiningHallScreen
import xyz.quickdev.dininghallcalendar.ui.DiningHallViewModel
import xyz.quickdev.dininghallcalendar.ui.theme.DiningHallCalendarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiningHallCalendarTheme {
                Column(modifier = Modifier.fillMaxSize().displayCutoutPadding().navigationBarsPadding()) {

                    DiningHallScreen(DiningHallViewModel())
                }
            }
        }
    }
}
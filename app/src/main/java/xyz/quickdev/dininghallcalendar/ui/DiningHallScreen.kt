package xyz.quickdev.dininghallcalendar.ui

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import xyz.quickdev.dininghallcalendar.ui.components.DiningHallDisplay

@Composable
fun DiningHallScreen(viewModel: DiningHallViewModel) {
    val diningHall by viewModel.diningHall

    if (diningHall == null) {
        CircularProgressIndicator()
    } else {
        DiningHallDisplay(diningHall!!, viewModel::updateDate, selectedMillis = viewModel.diningMillis.value)
    }
}
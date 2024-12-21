package xyz.quickdev.dininghallcalendar.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.quickdev.dininghallcalendar.DiningHall

@Composable
fun DiningHallDisplay(diningHall: DiningHall) {
    Column {
        Text(
            text = diningHall.hallName,
            modifier = Modifier.padding(all = 16.dp),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            diningHall.zoneList.forEach {
                DiningZoneDisplay(it)
            }
        }
    }
}
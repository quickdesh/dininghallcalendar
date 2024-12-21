package xyz.quickdev.dininghallcalendar.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import xyz.quickdev.dininghallcalendar.DiningFood
import xyz.quickdev.dininghallcalendar.Icon

@Composable
fun DiningFoodDisplay(diningFood: DiningFood) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = diningFood.name,
        )
        Spacer(modifier = Modifier.weight(1f))
        diningFood.labelList.forEach {
            it.Icon(modifier = Modifier.padding(horizontal = 2.dp))
        }
    }
}
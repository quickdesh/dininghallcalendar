package xyz.quickdev.dininghallcalendar.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import xyz.quickdev.dininghallcalendar.DiningMeal

@Composable
fun DiningMealDisplay(diningMeal: DiningMeal, s: SnapshotStateMap<Int, Boolean>) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(diningMeal.zoneList) { zone ->
            if (s[zone.hashCode()] == null) {
                s[zone.hashCode()] = true
            }
            val showFood = s[zone.hashCode()] != false
            val setShowFood: () -> Unit = {
                s[zone.hashCode()] = !s[zone.hashCode()]!!
            }
            DiningZoneDisplay(zone, showFood, setShowFood)
        }
    }
}
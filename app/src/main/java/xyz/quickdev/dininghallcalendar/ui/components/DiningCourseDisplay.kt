package xyz.quickdev.dininghallcalendar.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import xyz.quickdev.dininghallcalendar.DiningMeal

@Composable
fun DiningMealDisplay(diningMeal: DiningMeal, s: SnapshotStateMap<Int, Boolean>) {
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    LazyColumn(modifier = Modifier.fillMaxWidth(), state = lazyListState) {
        items(diningMeal.zoneList) { zone ->
            if (s[zone.hashCode()] == null) {
                s[zone.hashCode()] = true
            }
            val showFood = s[zone.hashCode()] != false
            val setShowFood: () -> Unit = {
                scope.launch {
                    val show = !s[zone.hashCode()]!!
                    s[zone.hashCode()] = show
                    // if (show) lazyListState.animateScrollToItem(diningMeal.zoneList.indexOf(zone))
                }
            }
            DiningZoneDisplay(zone, showFood, setShowFood)
        }
    }
}
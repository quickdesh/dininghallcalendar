package xyz.quickdev.dininghallcalendar.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.quickdev.dininghallcalendar.DiningHall

@Composable
fun DiningHallDisplay(diningHall: DiningHall) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabTitles = diningHall.mealList.map { it.mealName }

    Column {
        Column {
            Text(
                text = diningHall.hallName,
                modifier = Modifier.padding(all = 16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabTitles.forEachIndexed { index, title ->

                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }
        }

        val showFoodMap = remember { mutableStateMapOf<Int, Boolean>() }
        var previousTabIndex by remember { mutableIntStateOf(0) }

        AnimatedContent(
            targetState = selectedTabIndex,
            transitionSpec = {
                val direction = if (targetState > previousTabIndex) 1 else -1
                (slideInHorizontally { it * direction } + fadeIn()).togetherWith(
                    slideOutHorizontally { -it * direction } + fadeOut()
                )
            },
        ) { tabIndex ->
            previousTabIndex = tabIndex // Update the previous index
            DiningMealDisplay(diningHall.mealList[tabIndex], showFoodMap)
        }
    }
}
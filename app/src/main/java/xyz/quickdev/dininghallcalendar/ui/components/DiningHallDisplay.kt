package xyz.quickdev.dininghallcalendar.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.quickdev.dininghallcalendar.DiningHall
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DiningHallDisplay(diningHall: DiningHall, onDateSelected: (Long?) -> Unit, selectedMillis: Long) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val tabTitles = diningHall.mealList.map { it.mealName }
    var showDatePicker by remember { mutableStateOf(false) }
    val diningDate = SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT, Locale.getDefault())

    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = diningHall.hallName,
                modifier = Modifier.padding(all = 16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { showDatePicker = true }
            )  {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp)
                )
                Text(
                    text = diningDate.format(Date(selectedMillis)),
                    modifier = Modifier.padding(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if (showDatePicker) {
            DatePickerModal(
                onDateSelected = onDateSelected,
                onDismiss = { showDatePicker = false },
                selectedMillis = selectedMillis
            )
        }

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
            previousTabIndex = tabIndex
            DiningMealDisplay(diningHall.mealList[tabIndex], showFoodMap)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    selectedMillis: Long
) {
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = selectedMillis)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                onDateSelected(datePickerState.selectedDateMillis)
                onDismiss()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}
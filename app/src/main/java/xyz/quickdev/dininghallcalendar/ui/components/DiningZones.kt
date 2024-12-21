package xyz.quickdev.dininghallcalendar.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import xyz.quickdev.dininghallcalendar.FoodItem
import xyz.quickdev.dininghallcalendar.Icon

@Composable
fun DiningZone(
    zoneName: String,
    foodItems: List<FoodItem>
) {
    val interactionSource = remember { MutableInteractionSource() }
    val arrowRotation = remember { Animatable(-90f) }
    var showFood by remember { mutableStateOf(false) }

    LaunchedEffect(showFood) {
        arrowRotation.animateTo(
            targetValue = if (showFood) 0f else -90f,
            animationSpec = tween(durationMillis = 300),
        )
    }

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Box(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = ripple()
                    ) {
                        showFood = !showFood
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = zoneName,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontWeight = FontWeight.Bold
                    )
                    HorizontalDivider(
                        modifier = Modifier.weight(1f),
                        thickness = 2.dp,
                        color = Color.Black
                    )
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(24.dp)
                            .rotate(arrowRotation.value)
                    )
                }
            }

            AnimatedVisibility(
                visible = showFood,
                enter = expandVertically(animationSpec = tween(durationMillis = 300)),
                exit = shrinkVertically(animationSpec = tween(durationMillis = 300))
            ) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(foodItems.size) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = foodItems[index].name,
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            foodItems[index].labelList.forEach {
                                it.Icon(modifier = Modifier.padding(horizontal = 2.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}
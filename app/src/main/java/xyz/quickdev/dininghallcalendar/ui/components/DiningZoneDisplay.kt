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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.quickdev.dininghallcalendar.DiningZone

@Composable
fun DiningZoneDisplay(diningZone: DiningZone, showFood: Boolean, flipShowFood: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val arrowRotation = remember { Animatable(if (showFood) 0f else -90f) }


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
                    ) { flipShowFood() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    Text(
                        text = diningZone.zoneName,
                        modifier = Modifier.padding(horizontal = 8.dp),
                        fontSize = 14.sp,
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
                Column(modifier = Modifier.fillMaxWidth()) {
                    diningZone.foodList.forEach {
                        DiningFoodDisplay(it)
                    }
                }
            }
        }
    }
}
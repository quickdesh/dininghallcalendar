package xyz.quickdev.dininghallcalendar.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.quickdev.dininghallcalendar.DiningFood
import xyz.quickdev.dininghallcalendar.Icon

@Composable
fun DiningFoodDisplay(diningFood: DiningFood) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = diningFood.foodName,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(32.dp))

            diningFood.labelList.forEach {
                it.Icon(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
        StripedHorizontalDivider()
    }
}

@Composable
fun StripedHorizontalDivider(
    stripeWidth: Dp = 4.dp,
    stripeSpacing: Dp = 4.dp,
    color: Color = Color.Black,
    height: Dp = 2.dp
) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        val stripeWidthPx = stripeWidth.toPx()
        val stripeSpacingPx = stripeSpacing.toPx()
        var startX = 0f

        while (startX < size.width) {
            drawRect(
                color = color,
                topLeft = androidx.compose.ui.geometry.Offset(startX, 0f),
                size = Size(stripeWidthPx, size.height)
            )
            startX += stripeWidthPx + stripeSpacingPx
        }
    }
}
package xyz.quickdev.dininghallcalendar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Legend(
    label: String,
    color: Color,
){
    Box(
        modifier = Modifier.size(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier.size(24.dp).background(color = color, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Black,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

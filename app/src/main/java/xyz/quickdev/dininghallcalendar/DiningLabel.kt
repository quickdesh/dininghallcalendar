package xyz.quickdev.dininghallcalendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import xyz.quickdev.dininghallcalendar.ui.components.Legend

enum class DiningLabel(val description: String, val img: String, val label: String, val color: Long) {
    Dairy(description = "Dairy", img = "dairy.gif", label = "D", color = 0xFF0066aa),
    Eggs(description = "Eggs", img = "egg.gif", label = "E", color = 0xFFeecc55),
    Fish(description = "Fish", img = "fish.gif", label = "F", color = 0xFFee0066),
    Gluten(description = "Gluten", img = "gluten.gif", label = "G", color = 0xFFff5522),
    Nuts(description = "Nuts", img = "nuts.gif", label = "N", color = 0xFFee1122),
    Sesame(description = "Sesame", img = "sesame.gif", label = "SS", color = 0xFFff9900),
    Shellfish(description = "Shellfish", img = "Shellfish.png", label = "SF", color = 0xFF11aa99),
    Soy(description = "Soy", img = "soy.gif", label = "S", color = 0xFF88cc22),
    Halal(description = "Halal Friendly", img = "HalalFriendly.gif", label = "HF", color = 0xFF00aaee),
    Local(description = "Locally Grown", img = "local.gif", label = "L", color = 0xFF667788),
    Smart(description = "Smart Choice", img = "smartchoice.gif", label = "SC", color = 0xFFFFFFFF),
    Vegan(description = "Vegan", img = "vegan.gif", label = "VG", color = 0xFF773399),
    Vegetarian(description = "Vegetarian", img = "vegetarian.gif", label = "V", color = 0xFF006633);
}

@Composable
fun DiningLabel.Icon(modifier: Modifier = Modifier) {
    Legend(label = this.label, color = Color(this.color), modifier = modifier)
}
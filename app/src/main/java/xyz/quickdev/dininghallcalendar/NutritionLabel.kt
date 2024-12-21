package xyz.quickdev.dininghallcalendar

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import xyz.quickdev.dininghallcalendar.ui.components.Legend

enum class NutritionLabel(val description: String, val label: String, val color: Long) {
    Dairy(description = "Dairy", label = "D", color = 0xFF0066aa),
    Eggs(description = "Eggs", label = "E", color = 0xFFeecc55),
    Fish(description = "Fish", label = "F", color = 0xFFee0066),
    Gluten(description = "Gluten", label = "G", color = 0xFFff5522),
    Nuts(description = "Nuts", label = "N", color = 0xFFee1122),
    Sesame(description = "Sesame", label = "SS", color = 0xFFff9900),
    Shellfish(description = "Shellfish", label = "SF", color = 0xFF11aa99),
    Soy(description = "Soy", label = "S", color = 0xFF88cc22),
    Halal(description = "Halal Friendly", label = "HF", color = 0xFF00aaee),
    Local(description = "Locally Grown", label = "L", color = 0xFF667788),
    Vegan(description = "Vegan", label = "VG", color = 0xFF773399),
    Vegetarian(description = "Vegetarian", label = "V", color = 0xFF006633);
}

@Composable
fun NutritionLabel.Icon() { Legend(label = this.label, Color(this.color)) }
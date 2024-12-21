package xyz.quickdev.dininghallcalendar

data class DiningHall(
    val hallName: String,
    val zoneList: List<DiningZone>
)

data class DiningZone(
    val zoneName: String,
    val foodList: List<DiningFood>
)

data class DiningFood(
    val name: String,
    val labelList: List<NutritionLabel>
)
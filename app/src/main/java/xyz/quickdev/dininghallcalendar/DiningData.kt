package xyz.quickdev.dininghallcalendar

data class DiningHall(
    val hallName: String,
    val mealList: List<DiningMeal>
)

data class DiningMeal(
    val mealName: String,
    val zoneList: List<DiningZone>
)

data class DiningZone(
    val zoneName: String,
    val foodList: List<DiningFood>
)

data class DiningFood(
    val foodName: String,
    val labelList: List<DiningLabel>
)
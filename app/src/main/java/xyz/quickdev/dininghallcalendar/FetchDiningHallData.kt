package xyz.quickdev.dininghallcalendar

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import org.jsoup.Jsoup

suspend fun fetchAndParseWebPage(url: String): DiningHall? {

    var diningHall: DiningHall?
    var diningMeal: DiningMeal
    var diningZone: DiningZone
    var diningFood: DiningFood

    val client = HttpClient(CIO)

        try {

            val htmlContent: String = client.get(url).body()

            val document = Jsoup.parse(htmlContent)

            Log.i("qwert", "url =  $url")


            Log.i("qwert", "document =  ${document.toString().slice(0..100)}")

            val locationNumber = Regex(pattern = "(?<=locationNum=)\\d+").find(url)!!.value.toInt()
            val diningHallName = document.selectFirst("option[value=${locationNumber}]")!!.text()

            Log.i("qwert", "location Number = $locationNumber")
            Log.i("qwert", "dining hall name = $diningHallName")


            val mealList = mutableListOf<DiningMeal>()
            document.select("div[role=tabpanel]").forEach { mealClass ->
                val mealClassName =
                    document.selectFirst("a[aria-controls=${mealClass.attr("id")}]")!!.text()
                val zoneList = mutableListOf<DiningZone>()
                mealClass.select("div.card-body").forEach { zoneClass ->
                    val zoneClassName = zoneClass.select("h5.card-title").text()
                    val foodList = mutableListOf<DiningFood>()
                    zoneClass.select("div.row.menu-item-row").forEach { foodClass ->
                        val foodClassName = foodClass.selectFirst("a.menu-item-name")!!.text()
                        val labelList = mutableListOf<DiningLabel>()
                        foodClass.select("img.nutri-icon").forEach { labelClass ->
                            val matchingLabel = DiningLabel.entries.find {
                                labelClass.attr("src").contains(it.img)
                            }!!
                            labelList.add(matchingLabel)
                        }
                        diningFood = DiningFood(
                            foodName = foodClassName,
                            labelList = labelList
                        )
                        foodList.add(diningFood)
                    }
                    diningZone = DiningZone(
                        zoneName = zoneClassName,
                        foodList = foodList,
                    )
                    zoneList.add(diningZone)
                }
                diningMeal = DiningMeal(
                    mealName = mealClassName,
                    zoneList = zoneList,
                )
                mealList.add(diningMeal)
            }

            diningHall = DiningHall(
                hallName = diningHallName,
                mealList = mealList
            )

        } catch (e: Exception) {
            diningHall = null
        }

    return diningHall
}
package xyz.quickdev.dininghallcalendar.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import xyz.quickdev.dininghallcalendar.DiningHall
import xyz.quickdev.dininghallcalendar.fetchAndParseWebPage

class DiningHallViewModel : ViewModel() {

    private val _diningHall = mutableStateOf<DiningHall?>(null)
    val diningHall: State<DiningHall?> = _diningHall

    init {
        fetchDiningHallData()
    }

    private fun fetchDiningHallData() {
        viewModelScope.launch {
            val fetchedData = fetchAndParseWebPage("https://nutrition.umd.edu/?locationNum=19&dtdate=12/10/2024")
            _diningHall.value = fetchedData
        }
    }
}
package xyz.quickdev.dininghallcalendar.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import xyz.quickdev.dininghallcalendar.DiningHall
import xyz.quickdev.dininghallcalendar.fetchAndParseWebPage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DiningHallViewModel : ViewModel() {

    private val _diningHall = mutableStateOf<DiningHall?>(null)
    val diningHall: State<DiningHall?> = _diningHall

    private val _diningMillis = mutableLongStateOf(System.currentTimeMillis())
    val diningMillis: State<Long> = _diningMillis

    init {
        fetchDiningHallData()
    }

    private fun fetchDiningHallData() {
        viewModelScope.launch(Dispatchers.IO) {
            val diningDate = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(Date(diningMillis.value))
            val fetchedData = fetchAndParseWebPage("https://nutrition.umd.edu/?locationNum=19&dtdate=${diningDate}")
            _diningHall.value = fetchedData
        }
    }

    fun updateDate(millis: Long?) {
        millis?.let{
            _diningHall.value = null
            _diningMillis.longValue = millis
            fetchDiningHallData()
        }
    }
}
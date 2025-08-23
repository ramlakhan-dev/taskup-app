package com.rl.taskup.presentation.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DateTimePickerDialog(
    modifier: Modifier = Modifier,
    onDateTimeSelected: (LocalDateTime) -> Unit
) {

    val context = LocalContext.current

    var showDatePicker by remember { mutableStateOf(true) }
    var showTimePicker by remember { mutableStateOf(false) }

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var selectedTime by remember { mutableStateOf<LocalTime?>(null) }

    if (showDatePicker) {
        DatePickerDialog(
            modifier = Modifier.padding(16.dp),
            onDismissRequest = { showDatePicker = false },
            onDateChange = { millis ->
                selectedDate = Instant.ofEpochMilli(millis)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()

                showDatePicker = false
                showTimePicker = true
            }
        )
    }

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { showTimePicker = false },
            onTimeChange = { hour, minute ->
                selectedTime = LocalTime.of(hour, minute)
                showTimePicker = false

                val date = selectedDate ?: return@TimePickerDialog
                val dateTime = LocalDateTime.of(date, selectedTime!!)
                onDateTimeSelected(dateTime)
            }
        )
    }
}
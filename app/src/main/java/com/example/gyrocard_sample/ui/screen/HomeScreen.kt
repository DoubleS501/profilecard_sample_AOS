package com.example.gyrocard_sample.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gyrocard_sample.sensor.rememberGyroSensor
import com.example.gyrocard_sample.ui.component.GyroCard

@Composable
fun HomeScreen() {
    val (pitch, roll) = rememberGyroSensor()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        GyroCard(
            pitch = pitch,
            roll = roll
        )
    }
}

package com.example.gyrocard_sample.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gyrocard_sample.data.loadProfilesFromAssets
import com.example.gyrocard_sample.model.UserProfile
import com.example.gyrocard_sample.sensor.rememberGyroSensor
import com.example.gyrocard_sample.ui.component.GyroCard

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val profiles = remember { loadProfilesFromAssets(context) }

    val (pitch, roll) = rememberGyroSensor()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        if (profiles.isNotEmpty()) {
            GyroCard(
                pitch = pitch,
                roll = roll,
                profile = profiles[0]
            )
        } else {
            Text("프로필 정보를 불러올 수 없습니다.")
        }
    }
}

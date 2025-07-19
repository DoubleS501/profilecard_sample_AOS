package com.example.gyrocard_sample.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GyroCard(pitch: Float, roll: Float) {
    Card(
        modifier = Modifier
            .size(250.dp, 150.dp)
            .graphicsLayer(
                rotationX = -pitch / 3f,
                rotationY = -roll / 3f,
                shadowElevation = 12f,
                shape = RoundedCornerShape(16.dp),
                clip = true
            ),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3E8AFF)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "움직이는 카드",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}

package com.example.gyrocard_sample.ui.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GyroCard(pitch: Float, roll: Float) {
    // 애니메이션 적용된 회전 값
    val animatedPitch by animateFloatAsState(
        targetValue = -pitch / 3f,
        animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing)
    )
    val animatedRoll by animateFloatAsState(
        targetValue = -roll / 3f,
        animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing)
    )

    Card(
        modifier = Modifier
            .size(250.dp, 150.dp)
            .graphicsLayer(
                rotationX = animatedPitch,
                rotationY = animatedRoll,
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

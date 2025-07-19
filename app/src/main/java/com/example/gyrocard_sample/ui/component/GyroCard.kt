package com.example.gyrocard_sample.ui.component

import android.graphics.BitmapFactory
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gyrocard_sample.model.UserProfile
import java.io.InputStream

@Composable
fun GyroCard(pitch: Float, roll: Float, profile: UserProfile) {
    val animatedPitch by animateFloatAsState(
        targetValue = -pitch / 3f,
        animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing)
    )
    val animatedRoll by animateFloatAsState(
        targetValue = -roll / 3f,
        animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing)
    )

    val context = LocalContext.current
    val inputStream: InputStream = context.assets.open(profile.imagePath)
    val bitmap = BitmapFactory.decodeStream(inputStream)

    Card(
        modifier = Modifier
            .size(280.dp, 180.dp)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "${profile.name}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "${profile.stack}",
                fontSize = 14.sp,
                color = Color.White
            )
            Text(
                text = "MBTI: ${profile.mbti}",
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}

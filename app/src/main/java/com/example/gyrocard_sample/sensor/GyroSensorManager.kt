package com.example.gyrocard_sample.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberGyroSensor(): Pair<Float, Float> {
    val context = LocalContext.current
    val sensorManager = remember { context.getSystemService(Context.SENSOR_SERVICE) as SensorManager }
    val sensor = remember { sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR) }

    val pitch = remember { mutableStateOf(0f) }
    val roll = remember { mutableStateOf(0f) }

    // 반응 민감도 조절 계수
    val pitchDampingFactor = 0.1f  // 상하 방향 둔하게
    val rollDampingFactor = 1.0f   // 좌우는 그대로

    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                val rotationMatrix = FloatArray(9)
                SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values)

                val orientationAngles = FloatArray(3)
                SensorManager.getOrientation(rotationMatrix, orientationAngles)

                // pitch (상하)와 roll (좌우) 각도 계산
                val rawPitch = Math.toDegrees(orientationAngles[1].toDouble()).toFloat()
                val rawRoll = Math.toDegrees(orientationAngles[2].toDouble()).toFloat()

                pitch.value = rawPitch * pitchDampingFactor
                roll.value = rawRoll * rollDampingFactor
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_GAME)

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    return Pair(pitch.value, roll.value)
}

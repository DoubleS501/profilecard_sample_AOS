package com.example.gyrocard_sample.data

import android.content.Context
import android.util.Log
import com.example.gyrocard_sample.model.UserProfile
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun loadProfilesFromAssets(context: Context): List<UserProfile> {
    return try {
        val jsonString = context.assets.open("user_profiles.json")
            .bufferedReader()
            .use { it.readText() }

        Log.d("ProfileDebug", "Loaded JSON: $jsonString")

        val gson = Gson()
        val type = object : TypeToken<List<UserProfile>>() {}.type
        gson.fromJson(jsonString, type)
    } catch (e: Exception) {
        Log.e("JSON_LOAD", "Error parsing JSON", e)
        emptyList()
    }
}

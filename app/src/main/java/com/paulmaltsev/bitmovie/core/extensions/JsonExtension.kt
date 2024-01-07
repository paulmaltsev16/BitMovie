package com.paulmaltsev.bitmovie.core.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> fromJson(json: String?): List<T>? {
    if (json == null) {
        return null
    }
    val type = object : TypeToken<List<T>>() {}.type
    val gson = Gson()
    return gson.fromJson(json, type)
}

fun <T> List<T>.toJson(): String {
    return Gson().toJson(this)
}

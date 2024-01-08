package com.paulmaltsev.bitmovie.features.home.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.paulmaltsev.bitmovie.R

@Composable
fun MovieCollectionTitle(name: String) {
    Text(
        text = name,
        color = colorResource(id = R.color.main),
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    )
}
package com.paulmaltsev.bitmovie.core.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulmaltsev.bitmovie.R
import com.paulmaltsev.bitmovie.core.ui.theme.BitMovieTheme
import com.paulmaltsev.bitmovie.core.utils.VoidCallback

@Composable
fun AppButton(
    title: String,
    modifier: Modifier,
    onClick: VoidCallback
) {
    val color = colorResource(id = R.color.main)

    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(2.dp, color),
        shape = RoundedCornerShape(15),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = color),
        modifier = modifier
    ) {
        Text(text = title)
    }
}


@Preview
@Composable
fun AppButtonPreview() {
    BitMovieTheme {
        AppButton(
            title = "Hello there",
            modifier = Modifier.fillMaxWidth()
        ) {}
    }
}
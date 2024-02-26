package com.example.mobilbox.ui.component

import androidx.compose.material3.AssistChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ChipElement(
        modifier : Modifier = Modifier,
        name : String,
        onClick : () -> Unit = {},
) {
    AssistChip(
        modifier = modifier,
        onClick = { onClick() },
        label = { Text(text = name) },
    )
}

@Preview
@Composable
fun ChipElementPreview() {
    ChipElement(name = "Title") {}
}

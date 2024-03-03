package com.example.mobilbox.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilbox.R

@Composable
fun RatingProduct(rating: String) {
   Row(
      verticalAlignment = Alignment.CenterVertically
   ) {
      Text(
         text = rating,
         style = MaterialTheme.typography.bodyMedium,
      )
      Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_bx1)))
      Icon(
         Icons.Filled.Star,
         contentDescription = null,
         tint = MaterialTheme.colorScheme.primary,
         modifier = Modifier.size(AssistChipDefaults.IconSize)
      )
   }
}

@Preview
@Composable
fun RatingProductPreview() {
   RatingProduct(rating = "4.3")
}

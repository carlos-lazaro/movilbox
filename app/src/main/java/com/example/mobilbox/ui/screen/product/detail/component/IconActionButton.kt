package com.example.mobilbox.ui.screen.product.detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import com.example.mobilbox.R

@Composable
fun IconActionButton(
   modifier: Modifier = Modifier,
   icon: ImageVector,
   onClick: () -> Unit,
) {
   IconButton(
      onClick = { onClick() },
      modifier = modifier
         .padding(dimensionResource(R.dimen.padding_bx2))
         .background(
            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
            shape = CircleShape,
         )
   ) {
      Icon(
         icon,
         contentDescription = null,
         tint = MaterialTheme.colorScheme.primary,
      )
   }
}

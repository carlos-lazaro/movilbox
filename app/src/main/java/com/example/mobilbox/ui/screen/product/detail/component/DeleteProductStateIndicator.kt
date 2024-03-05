package com.example.mobilbox.ui.screen.product.detail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import com.example.mobilbox.R
import com.example.mobilbox.ui.screen.product.detail.ProductDetailViewModel

@Composable
fun DeleteProductStateIndicator(
   state: ProductDetailViewModel.ResourceState? = null,
   onSuccess: () -> Unit,
) {
   Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
         .fillMaxWidth()
         .padding(dimensionResource(R.dimen.padding_bx2))
   ) {
      when (state) {
         ProductDetailViewModel.ResourceState.Error -> {
            Text(
               text = stringResource(R.string.product_error_message_deleting),
               style = MaterialTheme.typography.bodyMedium.copy(
                  fontStyle = FontStyle.Italic,
                  color = MaterialTheme.colorScheme.tertiary,
               ),
               modifier = Modifier.alpha(0.8f)
            )
         }

         ProductDetailViewModel.ResourceState.Loading,
         -> {
            LinearProgressIndicator(
               modifier = Modifier.fillMaxWidth()
            )
         }

         ProductDetailViewModel.ResourceState.Success -> {
            onSuccess()
         }

         else -> Unit
      }
   }
}

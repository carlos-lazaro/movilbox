package com.example.mobilbox.ui.screen.product.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilbox.R
import com.example.mobilbox.ui.screen.product.home.HomeViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SyncDatabaseStateIndicator(
   stateSync: HomeViewModel.ResourceState,
   onReset: () -> Unit,
) {
   fun getCurrentTimeFormatted(time: Long): String {
      val dateFormat = SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.getDefault())
      return dateFormat.format(Date(time))
   }

   Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
         .fillMaxWidth()
         .padding(dimensionResource(R.dimen.padding_bx2))
   ) {
      when (stateSync) {
         HomeViewModel.ResourceState.Error -> {
            TextAndIconContainer(text = {
               Text(
                  text = stringResource(R.string.products_error_message_syncing),
                  style = MaterialTheme.typography.bodyMedium.copy(
                     fontStyle = FontStyle.Italic,
                     color = MaterialTheme.colorScheme.tertiary,
                  ),
                  modifier = Modifier.alpha(0.8f)
               )
            }, icon = {
               Icon(
                  Icons.Filled.Refresh, contentDescription = null,
                  tint = MaterialTheme.colorScheme.primary,
               )
            }) {
               onReset()
            }
         }

         HomeViewModel.ResourceState.Loading,
         -> {
            LinearProgressIndicator(
               modifier = Modifier
                  .fillMaxWidth()
            )
         }

         is HomeViewModel.ResourceState.Success -> {
            TextAndIconContainer(text = {
               Text(
                  text = "${stringResource(R.string.products_success_message_syncing)} ${
                     getCurrentTimeFormatted(stateSync.now)
                  }",
                  style = MaterialTheme.typography.bodyMedium.copy(
                     fontStyle = FontStyle.Italic,
                  ),
                  modifier = Modifier.alpha(0.8f)
               )
            }, icon = {
               Icon(
                  Icons.Filled.Refresh, contentDescription = null,
                  tint = MaterialTheme.colorScheme.primary,
               )
            }) {
               onReset()
            }
         }
      }
   }
}

@Composable
internal fun RowScope.TextAndIconContainer(
   text: @Composable () -> Unit,
   icon: @Composable () -> Unit,
   iconOnClick: () -> Unit,
) {
   Box(modifier = Modifier.weight(1f)) {
      text()
   }

   IconButton(
      onClick = {
         iconOnClick()
      },
   ) {
      icon()
   }
}

@Preview
@Composable
fun ResourceProgressIndicatorPreview1() {
   SyncDatabaseStateIndicator(HomeViewModel.ResourceState.Loading) {}
}

@Preview
@Composable
fun ResourceProgressIndicatorPreview2() {
   SyncDatabaseStateIndicator(HomeViewModel.ResourceState.Success(System.currentTimeMillis())) {}
}

@Preview
@Composable
fun ResourceProgressIndicatorPreview3() {
   SyncDatabaseStateIndicator(HomeViewModel.ResourceState.Error) {}
}

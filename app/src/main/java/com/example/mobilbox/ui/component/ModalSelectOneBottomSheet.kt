package com.example.mobilbox.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.mobilbox.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalSelectOneBottomSheet(
   @StringRes title: Int,
   elements: List<String>,
   onItemSelected: (String) -> Unit,
   onDismiss: () -> Unit,
) {
   val sheetState = rememberModalBottomSheetState()
   val scope = rememberCoroutineScope()

   ModalBottomSheet(
      sheetState = sheetState,
      content = {
         LazyColumn(
            state = rememberLazyListState(),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
               .fillMaxWidth()
               .padding(dimensionResource(R.dimen.padding_bx4))
         ) {
            item {
               Text(
                  text = stringResource(title),
                  style = MaterialTheme.typography.headlineMedium
               )
               Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_bx4)))
            }
            items(elements, key = { it }) { item ->
               ChipElement(name = item) {
                  onItemSelected(item)
                  scope.launch { sheetState.hide() }
               }
            }
         }
      },
      onDismissRequest = {
         onDismiss()
      }
   )
}

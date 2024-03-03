package com.example.mobilbox.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobilbox.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarProducts(onSearch: (String) -> Unit) {
   var text by rememberSaveable { mutableStateOf("") }
   val focusManager = LocalFocusManager.current

   fun onSearchEvent() {
      focusManager.clearFocus()
      if (text.trim().isNotBlank()) {
         onSearch(text.trim())
      }
   }

   SearchBar(
      query = text,
      onQueryChange = { text = it },
      onSearch = { onSearchEvent() },
      active = false,
      onActiveChange = {},
      placeholder = { Text(stringResource(id = R.string.message_search_bar_placeholder)) },
      trailingIcon = {
         IconButton(onClick = { onSearchEvent() }) {
            Icon(Icons.Default.Search, contentDescription = null)
         }
      },
      content = {},
      modifier = Modifier
         .fillMaxWidth()
         .padding(dimensionResource(R.dimen.padding_bx2))
   )
}

@Preview
@Composable
fun PreviewSearchBarProducts() {
   SearchBarProducts() {}
}

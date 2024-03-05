package com.example.mobilbox.ui.screen.product.detail.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun ProductActionsDropdown(
   modifier: Modifier = Modifier,
   onDelete: () -> Unit,
) {
   var expanded by remember { mutableStateOf(false) }

   Box(
      modifier = modifier
   ) {
      IconActionButton(
         icon = Icons.Filled.MoreVert,
      ) { expanded = true }
      DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
         DropdownMenuItem(text = { Text("Delete") }, onClick = {
            expanded = false
            onDelete()
         }, leadingIcon = {
            Icon(
               Icons.Outlined.Delete, contentDescription = null
            )
         })
      }
   }
}

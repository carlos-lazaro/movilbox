package com.example.mobilbox.ui.screen.product.detail.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel(images: List<String>, modifier: Modifier = Modifier) {
   val pagerState = rememberPagerState {
      images.size
   }
   HorizontalPager(
      state = pagerState, key = { it }, pageSize = PageSize.Fixed(300.dp), modifier = modifier
   ) {
      AsyncImage(
         contentScale = ContentScale.Fit,
         model = images[it],
         contentDescription = null,
         modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .aspectRatio(1f)
      )
   }
}

package com.example.mobilbox.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.mobilbox.R
import com.example.mobilbox.domain.model.Product

@Composable
fun ProductCard(
   modifier: Modifier = Modifier,
   product: Product,
   onClick: (Product) -> Unit,
) {
   Card(
      elevation = CardDefaults.cardElevation(dimensionResource(R.dimen.elevation_4x2)),
      modifier = modifier
         .padding(dimensionResource(R.dimen.padding_bx2))
         .clickable { onClick(product) }
   ) {
      Box {
         AsyncImage(
            contentScale = ContentScale.Fit,
            model = product.thumbnail,
            contentDescription = null,
            modifier = Modifier
               .background(MaterialTheme.colorScheme.background)
               .aspectRatio(1.5f)
         )
         if (product.stock < 1) {
            OutOfStock()
         }
      }
      Column(
         modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_bx2))
      ) {
         Text(
            text = product.title.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.bodyLarge,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
         )
         Text(
            text = product.brand.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.labelSmall,
         )
         Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
               .fillMaxWidth()
         ) {
            Text(
               text = "${stringResource(R.string.price_prefix)} ${product.price}",
               color = MaterialTheme.colorScheme.primary,
               style = MaterialTheme.typography.bodySmall,
            )
            RatingProduct(rating = product.rating.toString())
         }
      }
   }
}

@Composable
internal fun BoxScope.OutOfStock(modifier: Modifier = Modifier) {
   Box(
      modifier = modifier
         .align(Alignment.TopEnd)
         .padding(top = dimensionResource(R.dimen.padding_bx4))
         .background(
            MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(
               topStartPercent = 50,
               bottomStartPercent = 50,
            ),
         )
   ) {
      Text(
         text = stringResource(R.string.message_out_of_stock).uppercase(),
         style = MaterialTheme.typography.bodySmall,
         color = MaterialTheme.colorScheme.onSecondary,
         modifier = Modifier
            .padding(
               top = dimensionResource(R.dimen.padding_bx1),
               end = dimensionResource(R.dimen.padding_bx2),
               bottom = dimensionResource(R.dimen.padding_bx1),
               start = dimensionResource(R.dimen.padding_bx3),
            )
      )
   }
}

@Preview
@Composable
fun PreviewProductCard() {
   val product = Product.getFake()
   ProductCard(product = product) {}
}

@Preview
@Composable
fun PreviewProductCard2() {
   val product = Product.getFake()
   ProductCard(product = product.copy(stock = 0)) {}
}

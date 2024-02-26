package com.example.mobilbox.ui.screen.product.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.mobilbox.R
import com.example.mobilbox.domain.model.Product
import com.example.mobilbox.ui.MovilboxAppState
import com.example.mobilbox.ui.component.ChipElement
import com.example.mobilbox.ui.component.RatingProduct
import com.example.mobilbox.ui.rememberMovilboxAppState

@Composable
fun ProductDetailRoute(
        modifier : Modifier = Modifier,
        appState : MovilboxAppState,
        id : Int? = null,
        productDetailViewModel : ProductDetailViewModel = hiltViewModel(),
) {
    val uiState by productDetailViewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        productDetailViewModel.onEvent(ProductDetailEvent.OnSetId(id))
    }

    ProductDetailScreen(appState, modifier, uiState) { event ->
        productDetailViewModel.onEvent(event)
    }
}

@Composable
fun ProductDetailScreen(
        appState : MovilboxAppState,
        modifier : Modifier = Modifier,
        uiState : ProductDetailState,
        onEvent : (ProductDetailEvent) -> Unit,
) {
    val scrollState = rememberScrollState()

    uiState.product?.let { product ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize()
        ) {
            Box {
                AsyncImage(
                    contentScale = ContentScale.Fit,
                    model = product.thumbnail,
                    contentDescription = null,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background)
                        .aspectRatio(2f)
                )
                IconButton(
                    onClick = {
                        appState.navController.navigateUp()
                    },
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.padding_bx2))
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                            shape = CircleShape,
                        )
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(dimensionResource(R.dimen.padding_bx2))
            ) {
                Text(
                    text = product.title.replaceFirstChar { it.uppercase() },
                    fontWeight = FontWeight.Medium,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.padding_bx2))
                ) {
                    RatingProduct(rating = product.rating.toString())

                    if (product.stock < 1) {
                        Text(
                            text = stringResource(R.string.message_out_of_stock),
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Medium,
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.message_available_stock),
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                }

                Text(
                    text = product.brand,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                )

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_bx2)))
                Text(
                    text = "${stringResource(R.string.price_prefix)} ${product.price}",
                    textDecoration = TextDecoration.LineThrough,
                    modifier = Modifier
                        .alpha(0.6f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${stringResource(R.string.price_prefix)} ${product.getPriceWithDiscount()}",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_bx2)))
                    Text(
                        text = "${product.discountPercentage}% ${stringResource(R.string.discount_suffix)}",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_bx2)))

                ChipElement(name = product.category)

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_bx2)))
                ImageCarousel(images = product.images)
            }
        }
    }
}

@Composable
fun ImageCarousel(images : List<String>, modifier : Modifier = Modifier) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
    ) {
        items(images, key = { it }) { image ->
            AsyncImage(
                contentScale = ContentScale.Fit,
                model = image,
                contentDescription = null,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .aspectRatio(1f)
            )
        }
    }
}

@Preview
@Composable
fun ProductDetailScreenPreview() {
    ProductDetailScreen(
        appState = rememberMovilboxAppState(),
        uiState = ProductDetailState(
            product = Product.getFake()
        ),
    ) {}
}

@Preview
@Composable
fun ProductDetailScreenPreview2() {
    ProductDetailScreen(
        appState = rememberMovilboxAppState(),
        uiState = ProductDetailState(
            product = Product.getFake().copy(stock = 0)
        ),
    ) {}
}

package com.example.oytra.ui.screens.productScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.oytra.data.model.ProductsItem

@Composable
fun ProductListItem(
    product: ProductsItem,
    onOrderClick: (Int) -> Unit
) {

    var quantity by remember { mutableIntStateOf(product.moq) }


    val isBelowMoq = quantity < product.moq
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {


            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray.copy(alpha = 0.2f))
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "₹${product.price}",
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.Magenta,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Order Quantity", style = MaterialTheme.typography.labelMedium)
                    Text(
                        text = "Min Order: ${product.moq}",
                        style = MaterialTheme.typography.bodySmall,
                        color = if (isBelowMoq) Color.Red else Color.Gray
                    )
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                ) {
                    IconButton(onClick = { if (quantity > 0) quantity-- }) {
                        Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Decrease")
                    }
                    Text(
                        text = "$quantity",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    IconButton(onClick = { quantity++ }) {
                        Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Increase")
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Button(
                onClick = { onOrderClick(quantity) },
                enabled = !isBelowMoq && quantity > 0,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = when {
                        quantity == 0 -> "Select Quantity"
                        isBelowMoq -> "Need ${product.moq} minimum"
                        else -> "Place Order"
                    }
                )
            }
        }
    }
}
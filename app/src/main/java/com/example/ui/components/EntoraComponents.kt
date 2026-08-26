package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.DesktopWindows
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Headphones
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Watch
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.ui.theme.EntoraGreenDark
import com.example.ui.theme.EntoraGreenLight
import com.example.ui.theme.EntoraGreenPrimary
import com.example.ui.theme.EntoraOnGreenPrimary
import com.example.ui.theme.ThemeMode

fun getCategoryIcon(category: ProductCategory): ImageVector {
    return when (category) {
        ProductCategory.AUDIO -> Icons.Filled.Headphones
        ProductCategory.WEARABLES -> Icons.Filled.Watch
        ProductCategory.EDC_GEAR -> Icons.Filled.Build
        ProductCategory.CARRY -> Icons.Filled.Work
        ProductCategory.OPTICS -> Icons.Filled.CameraAlt
        ProductCategory.DESK_STUDIO -> Icons.Filled.DesktopWindows
        ProductCategory.POWER_CHARGING -> Icons.Filled.Bolt
        ProductCategory.TACTICAL -> Icons.Filled.Shield
        ProductCategory.CARBON_ACCESSORIES -> Icons.Filled.AccountBalanceWallet
        ProductCategory.ALL -> Icons.Filled.GridView
    }
}

@Composable
fun EntoraBrandLogo(
    modifier: Modifier = Modifier,
    iconSize: Int = 32
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Geometric Pure Emerald & Obsidian Logo Mark
        Box(
            modifier = Modifier
                .size(iconSize.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(EntoraGreenPrimary, EntoraGreenDark)
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_entora_mark),
                contentDescription = "XENTORA Emblem",
                tint = Color.White,
                modifier = Modifier.size((iconSize * 0.72).dp)
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        // Wordmark in High-Contrast White & Pure Emerald Green
        Column {
            Text(
                text = "XENTORA",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 3.sp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "PRECISION LAB & GEAR",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                ),
                color = EntoraGreenPrimary
            )
        }
    }
}

@Composable
fun EntoraTopBar(
    themeMode: ThemeMode,
    onToggleTheme: () -> Unit,
    modifier: Modifier = Modifier,
    selectedCurrency: com.example.viewmodel.CurrencyOption = com.example.viewmodel.CurrencyOption.USD,
    onCurrencyChanged: (com.example.viewmodel.CurrencyOption) -> Unit = {}
) {
    var currencyMenuExpanded by androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 2.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            EntoraBrandLogo()

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Currency Switcher Dropdown Button
                Box {
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(100.dp))
                            .clickable { currencyMenuExpanded = true }
                            .testTag("currency_selector_button"),
                        shape = RoundedCornerShape(100.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = selectedCurrency.code,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Black,
                                    letterSpacing = 0.5.sp
                                ),
                                color = EntoraGreenPrimary
                            )
                            Text(
                                text = selectedCurrency.symbol,
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    androidx.compose.material3.DropdownMenu(
                        expanded = currencyMenuExpanded,
                        onDismissRequest = { currencyMenuExpanded = false }
                    ) {
                        com.example.viewmodel.CurrencyOption.values().forEach { curr ->
                            androidx.compose.material3.DropdownMenuItem(
                                text = {
                                    Text(
                                        text = curr.displayName,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontWeight = if (curr == selectedCurrency) FontWeight.Black else FontWeight.Normal
                                        ),
                                        color = if (curr == selectedCurrency) EntoraGreenPrimary else MaterialTheme.colorScheme.onSurface
                                    )
                                },
                                onClick = {
                                    onCurrencyChanged(curr)
                                    currencyMenuExpanded = false
                                }
                            )
                        }
                    }
                }

                // Theme Toggle Pill
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .clickable { onToggleTheme() }
                        .testTag("theme_toggle_button"),
                    shape = RoundedCornerShape(100.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        val isDark = themeMode == ThemeMode.DARK
                        Icon(
                            imageVector = if (isDark) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Toggle Dark/Light Mode",
                            tint = if (isDark) EntoraGreenPrimary else MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = if (isDark) "LIGHT" else "DARK",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Black,
                                letterSpacing = 1.sp
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeroBanner(
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(210.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable { onExploreClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF090C10)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.img_hero_banner),
                contentDescription = "Xentora Flagship Collection Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Dramatic dark gradient overlay with Emerald Green undertone
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xF5090C10),
                                Color(0xD0090C10),
                                Color(0x6606281C)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Tag
                Surface(
                    shape = RoundedCornerShape(100.dp),
                    color = EntoraGreenPrimary,
                    modifier = Modifier.clip(RoundedCornerShape(100.dp))
                ) {
                    Text(
                        text = "NEW RELEASE • 2026",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        ),
                        color = Color(0xFF041C10),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }

                // Main Heading & Call to action
                Column {
                    Text(
                        text = "XENTORA X-SERIES",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp
                        ),
                        color = Color.White
                    )
                    Text(
                        text = "Precision engineered titanium & hi-res acoustics",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFCBD5E1),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = onExploreClick,
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = EntoraGreenPrimary,
                                contentColor = Color(0xFF041C10)
                            ),
                            modifier = Modifier
                                .height(36.dp)
                                .testTag("banner_explore_button")
                        ) {
                            Text(
                                text = "Shop Now",
                                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                            )
                        }

                        Text(
                            text = "Up to 20% off with code XEN20",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                            color = EntoraGreenLight
                        )
                    }
                }
            }
        }
    }
}

/**
 * High-tech custom artwork canvas displaying the product's actual name,
 * glowing category icon, series chip, and precision blueprint aesthetics.
 */
@Composable
fun ProductCardArtwork(
    product: Product,
    modifier: Modifier = Modifier,
    height: Dp = 180.dp
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF090C10),
                        Color(0xFF111722),
                        Color(0xFF07261C)
                    )
                )
            )
    ) {
        // Futuristic blueprint / isometric grid styling
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            // Glowing radial aura
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        EntoraGreenPrimary.copy(alpha = 0.25f),
                        EntoraGreenPrimary.copy(alpha = 0.05f),
                        Color.Transparent
                    ),
                    center = Offset(canvasWidth * 0.5f, canvasHeight * 0.45f),
                    radius = canvasWidth * 0.7f
                )
            )

            // Technical grid lines
            val step = 28.dp.toPx()
            var x = 0f
            while (x < canvasWidth) {
                drawLine(
                    color = Color(0x12FFFFFF),
                    start = Offset(x, 0f),
                    end = Offset(x, canvasHeight),
                    strokeWidth = 1f
                )
                x += step
            }
            var y = 0f
            while (y < canvasHeight) {
                drawLine(
                    color = Color(0x12FFFFFF),
                    start = Offset(0f, y),
                    end = Offset(canvasWidth, y),
                    strokeWidth = 1f
                )
                y += step
            }
        }

        // Center Content: Category Icon badge + Large Product Title + Tech Specs
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Technical Header Row: Category Badge + Model ID
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Category Chip / Tech Tag
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color(0xFF063723),
                    border = BorderStroke(1.dp, EntoraGreenPrimary.copy(alpha = 0.6f))
                ) {
                    Text(
                        text = product.category.title.split(" ").firstOrNull()?.uppercase() ?: "GEAR",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Black,
                            letterSpacing = 1.sp,
                            fontSize = 9.sp
                        ),
                        color = EntoraGreenLight,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }

                Text(
                    text = "XEN-ID #${product.id.takeLast(4)}",
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        fontSize = 9.sp
                    ),
                    color = Color(0x9994A3B8)
                )
            }

            // Central Visual: Category Icon in a glowing ring + Prominent Product Name inside image
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.padding(vertical = 2.dp)
            ) {
                // Glowing Icon Halo
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF090C10))
                        .border(1.5.dp, EntoraGreenPrimary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = getCategoryIcon(product.category),
                        contentDescription = product.name,
                        tint = EntoraGreenPrimary,
                        modifier = Modifier.size(22.dp)
                    )
                }

                // PRODUCT NAME prominent inside the image card!
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Black,
                        letterSpacing = 0.5.sp
                    ),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }

            // Bottom Tech Spec Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = product.series.uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.8.sp,
                        fontSize = 9.sp
                    ),
                    color = EntoraGreenPrimary
                )

                val specPreview = product.specs.values.firstOrNull() ?: "${product.rating} ★ OFFICIAL"
                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color(0x80000000),
                    border = BorderStroke(0.5.dp, Color(0x30FFFFFF))
                ) {
                    Text(
                        text = specPreview,
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Medium,
                            fontSize = 9.sp
                        ),
                        color = Color(0xFFE2E8F0),
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                        maxLines = 1
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    isWishlisted: Boolean,
    onProductClick: () -> Unit,
    onToggleWishlist: () -> Unit,
    onAddToCart: () -> Unit,
    modifier: Modifier = Modifier,
    formatPrice: (Double) -> String = { "$${String.format("%.2f", it)}" },
    onToggleCompare: (() -> Unit)? = null,
    isCompared: Boolean = false
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .clickable { onProductClick() }
            .testTag("product_card_${product.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            // Product Image Container with Wishlist Heart, Compare and Named Artwork
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                ProductCardArtwork(
                    product = product,
                    height = 180.dp
                )

                // Top Actions (Compare & Wishlist)
                Row(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    if (onToggleCompare != null) {
                        IconButton(
                            onClick = onToggleCompare,
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(if (isCompared) EntoraGreenPrimary else Color(0xCC090C10))
                                .border(1.dp, Color(0x30FFFFFF), CircleShape)
                                .testTag("compare_toggle_${product.id}")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Build,
                                contentDescription = "Compare Toggle",
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    // Heart Wishlist Button
                    IconButton(
                        onClick = onToggleWishlist,
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xCC090C10))
                            .border(1.dp, Color(0x30FFFFFF), CircleShape)
                            .testTag("wishlist_toggle_${product.id}")
                    ) {
                        Icon(
                            imageVector = if (isWishlisted) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = "Wishlist Toggle",
                            tint = if (isWishlisted) Color(0xFFEF4444) else Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // Product Details Block
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            ) {
                // Series Label
                Text(
                    text = product.series,
                    style = MaterialTheme.typography.labelSmall.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    ),
                    color = EntoraGreenPrimary
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Product Name
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                // Tagline / Subtitle
                Text(
                    text = product.tagline,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Rating & Review count
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Rating",
                        tint = EntoraGreenPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "${product.rating}",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "(${product.reviewCount})",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Price & Add to Cart button
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = formatPrice(product.price),
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Black),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (product.originalPrice != null) {
                            Text(
                                text = formatPrice(product.originalPrice),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    textDecoration = TextDecoration.LineThrough
                                ),
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Button(
                        onClick = onAddToCart,
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EntoraGreenPrimary,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .height(38.dp)
                            .testTag("add_to_cart_${product.id}")
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingBag,
                            contentDescription = "Add to Cart",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Add",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryFilterPills(
    selectedCategory: ProductCategory,
    onCategorySelected: (ProductCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProductCategory.values().forEach { category ->
            val isSelected = category == selectedCategory
            val bgColor by animateColorAsState(
                targetValue = if (isSelected) EntoraGreenPrimary else MaterialTheme.colorScheme.surfaceVariant,
                animationSpec = tween(200)
            )
            val textColor by animateColorAsState(
                targetValue = if (isSelected) Color(0xFF041C10) else MaterialTheme.colorScheme.onSurface,
                animationSpec = tween(200)
            )

            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(100.dp))
                    .clickable { onCategorySelected(category) }
                    .testTag("category_chip_${category.name}"),
                shape = RoundedCornerShape(100.dp),
                color = bgColor,
                border = if (isSelected) null else BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
            ) {
                Text(
                    text = category.title,
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    ),
                    color = textColor,
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                )
            }
        }
    }
}


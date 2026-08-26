package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.AuthModalSheet
import com.example.ui.components.CheckoutSheet
import com.example.ui.components.EntoraTopBar
import com.example.ui.components.GearAdvisorSheet
import com.example.ui.components.ProductCompareSheet
import com.example.ui.components.ProductDetailSheet
import com.example.ui.components.UserProfileSheet
import com.example.ui.components.VaultRewardsSheet
import com.example.ui.screens.AccountScreen
import com.example.ui.screens.CartScreen
import com.example.ui.screens.CatalogScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.WishlistScreen
import com.example.ui.theme.EntoraGreenPrimary
import com.example.ui.theme.EntoraTheme
import com.example.ui.theme.ThemeMode
import com.example.viewmodel.AuthSheetMode
import com.example.viewmodel.EntoraViewModel

enum class EntoraNavTab(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
) {
    HOME("Home", Icons.Filled.Home, Icons.Outlined.Home, "nav_tab_home"),
    CATALOG("Catalog", Icons.Filled.GridView, Icons.Outlined.GridView, "nav_tab_catalog"),
    WISHLIST("Vault", Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder, "nav_tab_wishlist"),
    CART("Bag", Icons.Filled.ShoppingBag, Icons.Outlined.ShoppingBag, "nav_tab_cart"),
    ACCOUNT("Account", Icons.Filled.Person, Icons.Outlined.Person, "nav_tab_account")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: EntoraViewModel = viewModel()
            val themeMode by viewModel.themeMode.collectAsState()

            val isDarkTheme = when (themeMode) {
                ThemeMode.DARK -> true
                ThemeMode.LIGHT -> false
                ThemeMode.SYSTEM -> isSystemInDarkTheme()
            }

            EntoraTheme(darkTheme = isDarkTheme) {
                EntoraApp(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun EntoraApp(
    viewModel: EntoraViewModel,
    modifier: Modifier = Modifier
) {
    val themeMode by viewModel.themeMode.collectAsState()
    val allProducts by viewModel.allProducts.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val sortOption by viewModel.sortOption.collectAsState()
    val priceFilter by viewModel.priceFilter.collectAsState()
    val inStockOnly by viewModel.inStockOnly.collectAsState()
    val selectedProduct by viewModel.selectedProduct.collectAsState()
    val cartItems by viewModel.cartItems.collectAsState()
    val wishlistIds by viewModel.wishlistIds.collectAsState()
    val appliedCoupon by viewModel.appliedCoupon.collectAsState()
    val orders by viewModel.orders.collectAsState()
    val userMessage by viewModel.userMessage.collectAsState()
    val catalogPage by viewModel.catalogPage.collectAsState()

    val currentUser by viewModel.currentUser.collectAsState()
    val isAuthSheetOpen by viewModel.isAuthSheetOpen.collectAsState()
    val isProfileSheetOpen by viewModel.isProfileSheetOpen.collectAsState()
    val isCheckoutSheetOpen by viewModel.isCheckoutSheetOpen.collectAsState()
    val pendingCheckout by viewModel.pendingCheckout.collectAsState()
    val authSheetMode by viewModel.authSheetMode.collectAsState()
    val isAuthLoading by viewModel.isAuthLoading.collectAsState()

    val isCompareSheetOpen by viewModel.isCompareSheetOpen.collectAsState()
    val compareProductIds by viewModel.compareProductIds.collectAsState()
    val isConciergeOpen by viewModel.isConciergeOpen.collectAsState()
    val isVaultRewardsOpen by viewModel.isVaultRewardsOpen.collectAsState()
    val selectedCurrency by viewModel.selectedCurrency.collectAsState()

    var currentTab by remember { mutableStateOf(EntoraNavTab.HOME) }
    val snackbarHostState = remember { SnackbarHostState() }

    // User snackbar notifications
    LaunchedEffect(userMessage) {
        userMessage?.let { msg ->
            snackbarHostState.showSnackbar(msg)
            viewModel.clearUserMessage()
        }
    }

    val totalCartCount = cartItems.sumOf { it.quantity }
    val totalWishlistCount = wishlistIds.size

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            EntoraTopBar(
                themeMode = themeMode,
                onToggleTheme = { viewModel.toggleDarkLight() },
                selectedCurrency = selectedCurrency,
                onCurrencyChanged = { viewModel.setCurrency(it) }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                EntoraNavTab.values().forEach { tab ->
                    val isSelected = tab == currentTab
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { currentTab = tab },
                        icon = {
                            when (tab) {
                                EntoraNavTab.CART -> {
                                    BadgedBox(
                                        badge = {
                                            if (totalCartCount > 0) {
                                                Badge(
                                                    containerColor = EntoraGreenPrimary,
                                                    contentColor = Color(0xFF041C10)
                                                ) {
                                                    Text(
                                                        text = totalCartCount.toString(),
                                                        fontWeight = FontWeight.Black,
                                                        fontSize = 9.sp
                                                    )
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                            contentDescription = tab.title,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                }
                                EntoraNavTab.WISHLIST -> {
                                    BadgedBox(
                                        badge = {
                                            if (totalWishlistCount > 0) {
                                                Badge(
                                                    containerColor = EntoraGreenPrimary,
                                                    contentColor = Color(0xFF041C10)
                                                ) {
                                                    Text(
                                                        text = totalWishlistCount.toString(),
                                                        fontWeight = FontWeight.Black,
                                                        fontSize = 9.sp
                                                    )
                                                }
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                            contentDescription = tab.title,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                }
                                else -> {
                                    Icon(
                                        imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                                        contentDescription = tab.title,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                            }
                        },
                        label = {
                            Text(
                                text = tab.title,
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                    fontSize = 11.sp
                                )
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = EntoraGreenPrimary,
                            selectedTextColor = EntoraGreenPrimary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            indicatorColor = EntoraGreenPrimary.copy(alpha = 0.15f)
                        ),
                        modifier = Modifier.testTag(tab.testTag)
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            AnimatedContent(
                targetState = currentTab,
                transitionSpec = { fadeIn() togetherWith fadeOut() },
                label = "ScreenTransition"
            ) { targetTab ->
                when (targetTab) {
                    EntoraNavTab.HOME -> {
                        HomeScreen(
                            products = viewModel.getFilteredProducts(),
                            selectedCategory = selectedCategory,
                            searchQuery = searchQuery,
                            wishlistIds = wishlistIds,
                            onCategorySelected = { viewModel.setCategory(it) },
                            onSearchChanged = { viewModel.setSearchQuery(it) },
                            onProductClick = { viewModel.selectProduct(it) },
                            onToggleWishlist = { viewModel.toggleWishlist(it.id) },
                            onAddToCart = { viewModel.addToCart(it) },
                            onViewAllClick = { currentTab = EntoraNavTab.CATALOG },
                            formatPrice = { viewModel.formatPrice(it) },
                            compareProductIds = compareProductIds,
                            onToggleCompare = { viewModel.toggleCompareProduct(it.id) },
                            onOpenConcierge = { viewModel.openConcierge() },
                            onOpenVaultRewards = { viewModel.openVaultRewards() },
                            onOpenCompareSheet = { viewModel.openCompareSheet() }
                        )
                    }
                    EntoraNavTab.CATALOG -> {
                        CatalogScreen(
                            products = viewModel.getPaginatedFilteredProducts(),
                            totalItemsCount = viewModel.getFilteredProducts().size,
                            currentPage = catalogPage,
                            totalPages = viewModel.getTotalFilteredPages(),
                            selectedCategory = selectedCategory,
                            searchQuery = searchQuery,
                            sortOption = sortOption,
                            priceFilter = priceFilter,
                            inStockOnly = inStockOnly,
                            wishlistIds = wishlistIds,
                            onCategorySelected = { viewModel.setCategory(it) },
                            onSearchChanged = { viewModel.setSearchQuery(it) },
                            onSortChanged = { viewModel.setSortOption(it) },
                            onPriceFilterChanged = { viewModel.setPriceFilter(it) },
                            onToggleInStockOnly = { viewModel.toggleInStockOnly() },
                            onPageSelected = { viewModel.setCatalogPage(it) },
                            onNextPage = { viewModel.nextCatalogPage() },
                            onPrevPage = { viewModel.prevCatalogPage() },
                            onProductClick = { viewModel.selectProduct(it) },
                            onToggleWishlist = { viewModel.toggleWishlist(it.id) },
                            onAddToCart = { viewModel.addToCart(it) },
                            formatPrice = { viewModel.formatPrice(it) },
                            compareProductIds = compareProductIds,
                            onToggleCompare = { viewModel.toggleCompareProduct(it.id) },
                            onOpenCompareSheet = { viewModel.openCompareSheet() }
                        )
                    }
                    EntoraNavTab.CART -> {
                        CartScreen(
                            cartItems = cartItems,
                            coupon = appliedCoupon,
                            isLoggedIn = currentUser != null,
                            onUpdateQuantity = { item, qty -> viewModel.updateCartQuantity(item, qty) },
                            onRemoveItem = { item -> viewModel.removeFromCart(item) },
                            onApplyCoupon = { code -> viewModel.applyCouponCode(code) },
                            onRemoveCoupon = { viewModel.removeCoupon() },
                            onCheckoutClick = { viewModel.openCheckout() },
                            onStartShopping = { currentTab = EntoraNavTab.CATALOG },
                            formatPrice = { viewModel.formatPrice(it) }
                        )
                    }
                    EntoraNavTab.WISHLIST -> {
                        val wishlistProducts = allProducts.filter { wishlistIds.contains(it.id) }
                        WishlistScreen(
                            wishlistProducts = wishlistProducts,
                            onProductClick = { viewModel.selectProduct(it) },
                            onRemoveWishlist = { viewModel.toggleWishlist(it.id) },
                            onAddToCart = { viewModel.addToCart(it) },
                            onExploreProducts = { currentTab = EntoraNavTab.CATALOG },
                            formatPrice = { viewModel.formatPrice(it) }
                        )
                    }
                    EntoraNavTab.ACCOUNT -> {
                        AccountScreen(
                            user = currentUser,
                            orders = orders,
                            themeMode = themeMode,
                            isAuthLoading = isAuthLoading,
                            onSignInWithGoogle = { name, email -> viewModel.signInWithGoogle(name, email) },
                            onSignUpWithGoogle = { name, email -> viewModel.signUpWithGoogle(name, email) },
                            onSignInWithEmail = { email, pass -> viewModel.signInWithEmail(email, pass) },
                            onSignUpWithEmail = { name, email, pass -> viewModel.signUpWithEmail(name, email, pass) },
                            onSignOut = { viewModel.signOut() },
                            onAddAddress = { viewModel.addSavedAddress(it) },
                            onRemoveAddress = { viewModel.removeSavedAddress(it) },
                            onToggleTheme = { viewModel.toggleDarkLight() },
                            onViewOrders = {
                                currentTab = EntoraNavTab.ACCOUNT
                            },
                            onExploreCatalog = { currentTab = EntoraNavTab.CATALOG }
                        )
                    }
                }
            }
        }

        // Product Details Sheet Modal with AR Inspector & Reviews
        selectedProduct?.let { product ->
            ProductDetailSheet(
                product = product,
                isWishlisted = wishlistIds.contains(product.id),
                formatPrice = { viewModel.formatPrice(it) },
                onDismiss = { viewModel.selectProduct(null) },
                onToggleWishlist = { viewModel.toggleWishlist(product.id) },
                onToggleCompare = { viewModel.toggleCompareProduct(product.id) },
                isCompared = compareProductIds.contains(product.id),
                onAddToCart = { prod, qty, color ->
                    viewModel.addToCart(prod, qty, color)
                },
                onAddReview = { review ->
                    viewModel.addProductReview(product.id, review)
                },
                defaultUserName = currentUser?.name
            )
        }

        // Express Checkout Modal Sheet
        if (isCheckoutSheetOpen) {
            CheckoutSheet(
                cartItems = cartItems,
                coupon = appliedCoupon,
                user = currentUser,
                onDismiss = { viewModel.closeCheckout() },
                onRequestAuth = { mode ->
                    viewModel.openAuthSheet(mode, fromCheckout = true)
                },
                onConfirmOrder = { address, paymentMethod ->
                    val placed = viewModel.placeOrder(address, paymentMethod)
                    if (placed != null) {
                        currentTab = EntoraNavTab.ACCOUNT
                    }
                },
                formatPrice = { viewModel.formatPrice(it) }
            )
        }

        // Auth Modal Sheet (Strict checkout gate or standard login/signup)
        if (isAuthSheetOpen) {
            AuthModalSheet(
                initialMode = authSheetMode,
                isLoading = isAuthLoading,
                isPendingCheckout = pendingCheckout,
                onDismiss = { viewModel.closeAuthSheet() },
                onSignInWithGoogle = { name, email -> viewModel.signInWithGoogle(name, email) },
                onSignUpWithGoogle = { name, email -> viewModel.signUpWithGoogle(name, email) },
                onSignInWithEmail = { email, pass -> viewModel.signInWithEmail(email, pass) },
                onSignUpWithEmail = { name, email, pass -> viewModel.signUpWithEmail(name, email, pass) }
            )
        }

        // Logged-in User Profile Dashboard Sheet
        if (isProfileSheetOpen && currentUser != null) {
            UserProfileSheet(
                user = currentUser!!,
                onDismiss = { viewModel.closeProfileSheet() },
                onSignOut = { viewModel.signOut() },
                onAddAddress = { viewModel.addSavedAddress(it) },
                onRemoveAddress = { viewModel.removeSavedAddress(it) },
                onViewOrders = { currentTab = EntoraNavTab.ACCOUNT }
            )
        }

        // Concierge AI Gear Advisor Sheet
        if (isConciergeOpen) {
            GearAdvisorSheet(
                allProducts = allProducts,
                formatPrice = { viewModel.formatPrice(it) },
                onDismiss = { viewModel.closeConcierge() },
                onProductClick = {
                    viewModel.closeConcierge()
                    viewModel.selectProduct(it)
                },
                onAddToCart = { prod ->
                    viewModel.addToCart(prod)
                }
            )
        }

        // Product Spec Comparison Sheet
        if (isCompareSheetOpen) {
            val comparedProducts = allProducts.filter { compareProductIds.contains(it.id) }
            ProductCompareSheet(
                products = comparedProducts,
                formatPrice = { viewModel.formatPrice(it) },
                onDismiss = { viewModel.closeCompareSheet() },
                onRemoveProduct = { prodId ->
                    viewModel.toggleCompareProduct(prodId)
                },
                onClearAll = {
                    viewModel.clearComparison()
                },
                onAddToCart = { prod ->
                    viewModel.addToCart(prod)
                },
                onProductClick = { prod ->
                    viewModel.closeCompareSheet()
                    viewModel.selectProduct(prod)
                }
            )
        }

        // Vault VIP Rewards Hub Sheet
        if (isVaultRewardsOpen) {
            VaultRewardsSheet(
                user = currentUser,
                onDismiss = { viewModel.closeVaultRewards() },
                onRequireLogin = {
                    viewModel.closeVaultRewards()
                    viewModel.openAuthSheet(AuthSheetMode.LOGIN)
                },
                onRedeemPerk = { cost, title, code ->
                    viewModel.redeemPointsForCoupon(cost, code, title)
                }
            )
        }
    }
}

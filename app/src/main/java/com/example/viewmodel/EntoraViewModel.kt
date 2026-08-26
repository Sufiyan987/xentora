package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.EntoraRepository
import com.example.model.CartItem
import com.example.model.MemberTier
import com.example.model.Order
import com.example.model.OrderStatus
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.model.ProductColor
import com.example.model.ProductReview
import com.example.model.SavedAddress
import com.example.model.UserAccount
import com.example.ui.theme.ThemeMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

data class Coupon(
    val code: String,
    val discountPercent: Double = 0.0,
    val flatDiscount: Double = 0.0,
    val description: String
)

enum class CurrencyOption(val code: String, val symbol: String, val rate: Double, val displayName: String) {
    USD("USD", "$", 1.0, "USD ($) - US Dollar"),
    EUR("EUR", "€", 0.92, "EUR (€) - Eurozone"),
    GBP("GBP", "£", 0.78, "GBP (£) - British Pound"),
    JPY("JPY", "¥", 155.0, "JPY (¥) - Japanese Yen"),
    CAD("CAD", "CA$", 1.36, "CAD (CA$) - Canada"),
    AUD("AUD", "A$", 1.52, "AUD (A$) - Australia")
}

enum class SortOption(val label: String) {
    FEATURED("Featured"),
    PRICE_LOW("Price: Low to High"),
    PRICE_HIGH("Price: High to Low"),
    RATING("Top Rated"),
    POPULAR("Most Reviews")
}

enum class PriceFilter(val label: String, val min: Double, val max: Double) {
    ALL("All Prices", 0.0, 10000.0),
    UNDER_50("Under $50", 0.0, 50.0),
    FROM_50_TO_150("$50 - $150", 50.0, 150.0),
    FROM_150_TO_300("$150 - $300", 150.0, 300.0),
    OVER_300("$300+", 300.0, 10000.0)
}

enum class AuthSheetMode {
    LOGIN,
    SIGNUP,
    FORGOT_PASSWORD,
    GOOGLE_PICKER
}

class EntoraViewModel : ViewModel() {

    // Theme Mode
    private val _themeMode = MutableStateFlow(ThemeMode.DARK)
    val themeMode: StateFlow<ThemeMode> = _themeMode.asStateFlow()

    // Products & Filtering
    private val _allProducts = MutableStateFlow(EntoraRepository.sampleProducts)
    val allProducts: StateFlow<List<Product>> = _allProducts.asStateFlow()

    private val _selectedCategory = MutableStateFlow(ProductCategory.ALL)
    val selectedCategory: StateFlow<ProductCategory> = _selectedCategory.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _sortOption = MutableStateFlow(SortOption.FEATURED)
    val sortOption: StateFlow<SortOption> = _sortOption.asStateFlow()

    private val _priceFilter = MutableStateFlow(PriceFilter.ALL)
    val priceFilter: StateFlow<PriceFilter> = _priceFilter.asStateFlow()

    private val _inStockOnly = MutableStateFlow(false)
    val inStockOnly: StateFlow<Boolean> = _inStockOnly.asStateFlow()

    // Active Selected Product Details
    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct.asStateFlow()

    // Authentication & User State (Starts empty/unauthenticated)
    private val _currentUser = MutableStateFlow<UserAccount?>(null)
    val currentUser: StateFlow<UserAccount?> = _currentUser.asStateFlow()

    private val _isAuthSheetOpen = MutableStateFlow(false)
    val isAuthSheetOpen: StateFlow<Boolean> = _isAuthSheetOpen.asStateFlow()

    private val _isProfileSheetOpen = MutableStateFlow(false)
    val isProfileSheetOpen: StateFlow<Boolean> = _isProfileSheetOpen.asStateFlow()

    private val _authSheetMode = MutableStateFlow(AuthSheetMode.LOGIN)
    val authSheetMode: StateFlow<AuthSheetMode> = _authSheetMode.asStateFlow()

    private val _isAuthLoading = MutableStateFlow(false)
    val isAuthLoading: StateFlow<Boolean> = _isAuthLoading.asStateFlow()

    // Strict Checkout Auth Flag & Checkout Sheet State
    private val _pendingCheckout = MutableStateFlow(false)
    val pendingCheckout: StateFlow<Boolean> = _pendingCheckout.asStateFlow()

    private val _isCheckoutSheetOpen = MutableStateFlow(false)
    val isCheckoutSheetOpen: StateFlow<Boolean> = _isCheckoutSheetOpen.asStateFlow()

    // Selected Order for Live Tracking Modal
    private val _selectedOrderForTracking = MutableStateFlow<Order?>(null)
    val selectedOrderForTracking: StateFlow<Order?> = _selectedOrderForTracking.asStateFlow()

    // Cart (Starts empty)
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    // Wishlist (Starts empty)
    private val _wishlistIds = MutableStateFlow<Set<String>>(emptySet())
    val wishlistIds: StateFlow<Set<String>> = _wishlistIds.asStateFlow()

    // Applied Coupon (Starts empty)
    private val _appliedCoupon = MutableStateFlow<Coupon?>(null)
    val appliedCoupon: StateFlow<Coupon?> = _appliedCoupon.asStateFlow()

    // Placed Orders (Starts empty)
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders.asStateFlow()

    // Pagination for Catalog (12 items per page)
    private val _catalogPage = MutableStateFlow(1)
    val catalogPage: StateFlow<Int> = _catalogPage.asStateFlow()
    val pageSize = 12

    // Currency System
    private val _selectedCurrency = MutableStateFlow(CurrencyOption.USD)
    val selectedCurrency: StateFlow<CurrencyOption> = _selectedCurrency.asStateFlow()

    // Compare System (Up to 3 products)
    private val _compareProductIds = MutableStateFlow<Set<String>>(emptySet())
    val compareProductIds: StateFlow<Set<String>> = _compareProductIds.asStateFlow()

    private val _isCompareSheetOpen = MutableStateFlow(false)
    val isCompareSheetOpen: StateFlow<Boolean> = _isCompareSheetOpen.asStateFlow()

    // VIP Concierge Gear Advisor
    private val _isConciergeSheetOpen = MutableStateFlow(false)
    val isConciergeSheetOpen: StateFlow<Boolean> = _isConciergeSheetOpen.asStateFlow()
    val isConciergeOpen: StateFlow<Boolean> = _isConciergeSheetOpen.asStateFlow()

    // Vault Rewards Hub
    private val _isVaultRewardsSheetOpen = MutableStateFlow(false)
    val isVaultRewardsSheetOpen: StateFlow<Boolean> = _isVaultRewardsSheetOpen.asStateFlow()
    val isVaultRewardsOpen: StateFlow<Boolean> = _isVaultRewardsSheetOpen.asStateFlow()

    // Notification / Toast Message
    private val _userMessage = MutableStateFlow<String?>(null)
    val userMessage: StateFlow<String?> = _userMessage.asStateFlow()

    fun setCurrency(currency: CurrencyOption) {
        _selectedCurrency.value = currency
        _userMessage.value = "Currency switched to ${currency.code}"
    }

    fun formatPrice(amountInUSD: Double): String {
        val curr = _selectedCurrency.value
        val converted = amountInUSD * curr.rate
        return if (curr == CurrencyOption.JPY) {
            "${curr.symbol}${String.format(Locale.US, "%,d", converted.toInt())}"
        } else {
            "${curr.symbol}${String.format(Locale.US, "%,.2f", converted)}"
        }
    }

    fun toggleCompare(productId: String) {
        val current = _compareProductIds.value.toMutableSet()
        if (current.contains(productId)) {
            current.remove(productId)
            _userMessage.value = "Removed from comparison"
        } else {
            if (current.size >= 3) {
                _userMessage.value = "You can compare up to 3 products at a time"
                return
            }
            current.add(productId)
            _userMessage.value = "Added to comparison matrix (${current.size}/3)"
        }
        _compareProductIds.value = current
    }

    fun toggleCompareProduct(productId: String) = toggleCompare(productId)

    fun clearCompare() {
        _compareProductIds.value = emptySet()
        _isCompareSheetOpen.value = false
    }

    fun clearComparison() = clearCompare()

    fun openCompareSheet() {
        if (_compareProductIds.value.isEmpty()) {
            _userMessage.value = "Select products to compare first"
            return
        }
        _isCompareSheetOpen.value = true
    }

    fun closeCompareSheet() {
        _isCompareSheetOpen.value = false
    }

    fun openConcierge() {
        _isConciergeSheetOpen.value = true
    }

    fun closeConcierge() {
        _isConciergeSheetOpen.value = false
    }

    fun openVaultRewards() {
        _isVaultRewardsSheetOpen.value = true
    }

    fun closeVaultRewards() {
        _isVaultRewardsSheetOpen.value = false
    }

    fun addReviewToProduct(productId: String, review: ProductReview) {
        val updatedProducts = _allProducts.value.map { prod ->
            if (prod.id == productId) {
                val updatedReviews = listOf(review) + prod.reviews
                val newRating = updatedReviews.map { it.rating }.average().toFloat()
                prod.copy(
                    reviews = updatedReviews,
                    reviewCount = prod.reviewCount + 1,
                    rating = String.format(Locale.US, "%.1f", newRating).toFloat()
                )
            } else {
                prod
            }
        }
        _allProducts.value = updatedProducts
        // Update selectedProduct if currently open
        if (_selectedProduct.value?.id == productId) {
            _selectedProduct.value = updatedProducts.firstOrNull { it.id == productId }
        }
        _userMessage.value = "Review submitted! Thank you for your feedback."
    }

    fun addProductReview(productId: String, review: ProductReview) = addReviewToProduct(productId, review)

    fun redeemVaultReward(pointsCost: Int, perkTitle: String, couponCode: String): Boolean {
        val user = _currentUser.value
        if (user == null) {
            openAuthSheet(AuthSheetMode.LOGIN)
            return false
        }
        if (user.goldRewardPoints < pointsCost) {
            _userMessage.value = "Insufficient Vault points. You have ${user.goldRewardPoints} pts."
            return false
        }
        _currentUser.value = user.copy(goldRewardPoints = user.goldRewardPoints - pointsCost)
        _appliedCoupon.value = Coupon(
            code = couponCode,
            discountPercent = 0.0,
            flatDiscount = 15.0,
            description = perkTitle
        )
        _userMessage.value = "$perkTitle applied to your cart! (-${pointsCost} pts)"
        _isVaultRewardsSheetOpen.value = false
        return true
    }

    fun redeemPointsForCoupon(pointsCost: Int, couponCode: String, perkTitle: String): Boolean {
        return redeemVaultReward(pointsCost, perkTitle, couponCode)
    }

    fun setThemeMode(mode: ThemeMode) {
        _themeMode.value = mode
    }

    fun toggleDarkLight() {
        _themeMode.value = if (_themeMode.value == ThemeMode.DARK) ThemeMode.LIGHT else ThemeMode.DARK
    }

    fun setCategory(category: ProductCategory) {
        _selectedCategory.value = category
        _catalogPage.value = 1
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
        _catalogPage.value = 1
    }

    fun setSortOption(sort: SortOption) {
        _sortOption.value = sort
        _catalogPage.value = 1
    }

    fun setPriceFilter(filter: PriceFilter) {
        _priceFilter.value = filter
        _catalogPage.value = 1
    }

    fun toggleInStockOnly() {
        _inStockOnly.value = !_inStockOnly.value
        _catalogPage.value = 1
    }

    fun setCatalogPage(page: Int) {
        val total = getTotalFilteredPages().coerceAtLeast(1)
        _catalogPage.value = page.coerceIn(1, total)
    }

    fun nextCatalogPage() {
        setCatalogPage(_catalogPage.value + 1)
    }

    fun prevCatalogPage() {
        setCatalogPage(_catalogPage.value - 1)
    }

    fun selectProduct(product: Product?) {
        _selectedProduct.value = product
    }

    // Auth Sheet Controls
    fun openAuthSheet(mode: AuthSheetMode = AuthSheetMode.LOGIN, fromCheckout: Boolean = false) {
        _authSheetMode.value = mode
        if (fromCheckout) {
            _pendingCheckout.value = true
        }
        _isAuthSheetOpen.value = true
    }

    fun closeAuthSheet() {
        _isAuthSheetOpen.value = false
        _pendingCheckout.value = false
    }

    fun openCheckout() {
        if (_cartItems.value.isEmpty()) {
            _userMessage.value = "Your bag is empty. Add products to checkout."
            return
        }
        if (_currentUser.value == null) {
            _pendingCheckout.value = true
            _authSheetMode.value = AuthSheetMode.LOGIN
            _isAuthSheetOpen.value = true
            _userMessage.value = "Authentication Required: Please sign in or create an account to proceed with your order."
            return
        }
        _isCheckoutSheetOpen.value = true
    }

    fun closeCheckout() {
        _isCheckoutSheetOpen.value = false
    }

    fun trackOrder(order: Order?) {
        _selectedOrderForTracking.value = order
    }

    fun openProfileSheet() {
        if (_currentUser.value == null) {
            openAuthSheet(AuthSheetMode.LOGIN)
        } else {
            _isProfileSheetOpen.value = true
        }
    }

    fun closeProfileSheet() {
        _isProfileSheetOpen.value = false
    }

    private fun handleAuthSuccess(userName: String, welcomeBonusMsg: String) {
        _isAuthLoading.value = false
        _isAuthSheetOpen.value = false
        if (_pendingCheckout.value) {
            _pendingCheckout.value = false
            _isCheckoutSheetOpen.value = true
            _userMessage.value = "Welcome back, $userName! Resuming your order checkout."
        } else {
            _userMessage.value = welcomeBonusMsg
        }
    }

    // Google Sign-In / Sign-Up
    fun signInWithGoogle(customName: String? = null, customEmail: String? = null) {
        _isAuthLoading.value = true
        viewModelScope.launch {
            kotlinx.coroutines.delay(450) // smooth UX transition
            val name = customName ?: "Julian Vance"
            val email = customEmail ?: "julian.vance@gmail.com"
            val user = UserAccount(
                id = "usr_g_${UUID.randomUUID().toString().take(8)}",
                name = name,
                email = email,
                memberTier = MemberTier.FOUNDER,
                goldRewardPoints = 2500,
                joinedDate = "Verified Google Account",
                isGoogleAccount = true
            )
            _currentUser.value = user
            handleAuthSuccess(name, "Signed in with Google as $name")
        }
    }

    fun signUpWithGoogle(customName: String? = null, customEmail: String? = null) {
        _isAuthLoading.value = true
        viewModelScope.launch {
            kotlinx.coroutines.delay(450)
            val name = customName ?: "New ENTORA Member"
            val email = customEmail ?: "member@gmail.com"
            val user = UserAccount(
                id = "usr_g_${UUID.randomUUID().toString().take(8)}",
                name = name,
                email = email,
                memberTier = MemberTier.TITANIUM,
                goldRewardPoints = 1000,
                joinedDate = "Welcome Bonus +1,000 Pts",
                isGoogleAccount = true
            )
            _currentUser.value = user
            handleAuthSuccess(name, "Welcome to ENTORA, $name! Google account connected.")
        }
    }

    fun signInWithEmail(email: String, pass: String): Boolean {
        if (email.isBlank() || pass.isBlank()) {
            _userMessage.value = "Please enter your email and password"
            return false
        }
        if (!email.contains("@") || !email.contains(".")) {
            _userMessage.value = "Please enter a valid email address"
            return false
        }
        if (pass.length < 6) {
            _userMessage.value = "Password must be at least 6 characters"
            return false
        }
        _isAuthLoading.value = true
        viewModelScope.launch {
            kotlinx.coroutines.delay(350)
            val name = email.substringBefore("@").replace(".", " ").split(" ")
                .joinToString(" ") { it.replaceFirstChar { char -> if (char.isLowerCase()) char.titlecase(Locale.ROOT) else char.toString() } }
            val user = UserAccount(
                id = "usr_email_${UUID.randomUUID().toString().take(8)}",
                name = name,
                email = email.trim(),
                memberTier = MemberTier.FOUNDER,
                goldRewardPoints = 1500,
                joinedDate = "Member since 2026",
                isGoogleAccount = false
            )
            _currentUser.value = user
            handleAuthSuccess(name, "Signed in as $name")
        }
        return true
    }

    fun signUpWithEmail(name: String, email: String, pass: String): Boolean {
        if (name.isBlank() || email.isBlank() || pass.length < 6) {
            _userMessage.value = "Please enter your name, email, and password (min 6 chars)"
            return false
        }
        if (!email.contains("@") || !email.contains(".")) {
            _userMessage.value = "Please enter a valid email address"
            return false
        }
        _isAuthLoading.value = true
        viewModelScope.launch {
            kotlinx.coroutines.delay(350)
            val user = UserAccount(
                id = "usr_reg_${UUID.randomUUID().toString().take(8)}",
                name = name.trim(),
                email = email.trim(),
                memberTier = MemberTier.TITANIUM,
                goldRewardPoints = 1000,
                joinedDate = "Joined 2026",
                isGoogleAccount = false
            )
            _currentUser.value = user
            handleAuthSuccess(name.trim(), "Welcome to ENTORA, ${name.trim()}! +1,000 Reward Points added.")
        }
        return true
    }

    fun signOut() {
        _currentUser.value = null
        _isProfileSheetOpen.value = false
        _userMessage.value = "Signed out of ENTORA"
    }

    fun addSavedAddress(address: SavedAddress) {
        val current = _currentUser.value ?: return
        val updated = current.savedAddresses + address
        _currentUser.value = current.copy(savedAddresses = updated)
        _userMessage.value = "New delivery address saved"
    }

    fun removeSavedAddress(id: String) {
        val current = _currentUser.value ?: return
        val updated = current.savedAddresses.filter { it.id != id }
        _currentUser.value = current.copy(savedAddresses = updated)
        _userMessage.value = "Address removed"
    }

    fun isWishlisted(productId: String): Boolean {
        return _wishlistIds.value.contains(productId)
    }

    fun toggleWishlist(productId: String) {
        val current = _wishlistIds.value.toMutableSet()
        if (current.contains(productId)) {
            current.remove(productId)
            _userMessage.value = "Removed from ENTORA Vault"
        } else {
            current.add(productId)
            _userMessage.value = "Saved to ENTORA Vault"
        }
        _wishlistIds.value = current
    }

    fun addToCart(product: Product, quantity: Int = 1, color: ProductColor? = null) {
        val selectedColor = color ?: product.availableColors.firstOrNull() ?: ProductColor("Default", 0xFF14171C)
        val currentList = _cartItems.value.toMutableList()
        val existingIndex = currentList.indexOfFirst { it.product.id == product.id && it.selectedColor.name == selectedColor.name }

        if (existingIndex >= 0) {
            val existing = currentList[existingIndex]
            currentList[existingIndex] = existing.copy(quantity = existing.quantity + quantity)
        } else {
            currentList.add(CartItem(product, quantity, selectedColor))
        }
        _cartItems.value = currentList
        _userMessage.value = "Added ${product.name} to Cart"
    }

    fun updateCartQuantity(item: CartItem, newQuantity: Int) {
        if (newQuantity <= 0) {
            removeFromCart(item)
            return
        }
        val currentList = _cartItems.value.toMutableList()
        val index = currentList.indexOfFirst { it.product.id == item.product.id && it.selectedColor.name == item.selectedColor.name }
        if (index >= 0) {
            currentList[index] = item.copy(quantity = newQuantity)
            _cartItems.value = currentList
        }
    }

    fun removeFromCart(item: CartItem) {
        val currentList = _cartItems.value.toMutableList()
        currentList.removeAll { it.product.id == item.product.id && it.selectedColor.name == item.selectedColor.name }
        _cartItems.value = currentList
        _userMessage.value = "Item removed from Cart"
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    fun applyCouponCode(code: String): Boolean {
        val normalized = code.trim().uppercase()
        return when (normalized) {
            "ENTORA15", "ENTORA" -> {
                _appliedCoupon.value = Coupon(normalized, discountPercent = 0.15, description = "15% Launch Offer")
                _userMessage.value = "Coupon ENTORA15 applied (15% OFF)"
                true
            }
            "GOLD20", "GOLD" -> {
                _appliedCoupon.value = Coupon(normalized, discountPercent = 0.20, description = "20% Gold Member Special")
                _userMessage.value = "Coupon GOLD20 applied (20% OFF)"
                true
            }
            "SAVE50" -> {
                _appliedCoupon.value = Coupon(normalized, flatDiscount = 50.0, description = "$50 Off Orders")
                _userMessage.value = "Coupon SAVE50 applied ($50 OFF)"
                true
            }
            else -> {
                _userMessage.value = "Invalid coupon code. Try ENTORA15"
                false
            }
        }
    }

    fun removeCoupon() {
        _appliedCoupon.value = null
        _userMessage.value = "Coupon removed"
    }

    fun placeOrder(
        shippingAddress: String = "742 Evergreen Terrace, Penthouse 14B, San Francisco, CA",
        paymentMethod: String = "Google Pay"
    ): Order? {
        val user = _currentUser.value
        if (user == null) {
            _pendingCheckout.value = true
            _authSheetMode.value = AuthSheetMode.LOGIN
            _isAuthSheetOpen.value = true
            _userMessage.value = "Strict Security: Please sign in or create an account to complete your order."
            return null
        }

        val items = _cartItems.value
        if (items.isEmpty()) {
            _userMessage.value = "Your cart is empty."
            return null
        }

        val subtotal = items.sumOf { it.totalPrice }
        val coupon = _appliedCoupon.value
        val discount = if (coupon != null) {
            if (coupon.discountPercent > 0) subtotal * coupon.discountPercent else coupon.flatDiscount
        } else 0.0
        val shipping = if (subtotal > 150.0) 0.0 else 15.0
        val total = (subtotal - discount + shipping).coerceAtLeast(0.0)

        val newOrder = Order(
            id = "ENT-" + (100000..999999).random(),
            date = SimpleDateFormat("MMM dd, yyyy", Locale.US).format(Date()),
            items = items,
            subtotal = subtotal,
            discount = discount,
            shipping = shipping,
            total = total,
            status = OrderStatus.PROCESSING,
            trackingNumber = "ENT-TRACK-" + UUID.randomUUID().toString().take(8).uppercase(),
            shippingAddress = shippingAddress,
            paymentMethod = paymentMethod
        )

        _orders.value = listOf(newOrder) + _orders.value
        _cartItems.value = emptyList()
        _isCheckoutSheetOpen.value = false

        // Award reward points for purchase
        val earnedPoints = (total * 5).toInt()
        _currentUser.value = user.copy(goldRewardPoints = user.goldRewardPoints + earnedPoints)
        _userMessage.value = "Order #${newOrder.id} placed! +$earnedPoints Reward Points earned!"

        return newOrder
    }

    fun clearUserMessage() {
        _userMessage.value = null
    }

    fun getCategoryCount(category: ProductCategory): Int {
        return if (category == ProductCategory.ALL) {
            _allProducts.value.size
        } else {
            _allProducts.value.count { it.category == category }
        }
    }

    fun getFilteredProducts(): List<Product> {
        var list = _allProducts.value

        if (_selectedCategory.value != ProductCategory.ALL) {
            list = list.filter { it.category == _selectedCategory.value }
        }

        val price = _priceFilter.value
        if (price != PriceFilter.ALL) {
            list = list.filter { it.price in price.min..price.max }
        }

        if (_inStockOnly.value) {
            list = list.filter { it.inStock }
        }

        val query = _searchQuery.value.trim().lowercase()
        if (query.isNotEmpty()) {
            list = list.filter {
                it.name.lowercase().contains(query) ||
                it.tagline.lowercase().contains(query) ||
                it.description.lowercase().contains(query) ||
                it.series.lowercase().contains(query) ||
                it.tags.any { tag -> tag.lowercase().contains(query) }
            }
        }

        return when (_sortOption.value) {
            SortOption.FEATURED -> list
            SortOption.PRICE_LOW -> list.sortedBy { it.price }
            SortOption.PRICE_HIGH -> list.sortedByDescending { it.price }
            SortOption.RATING -> list.sortedByDescending { it.rating }
            SortOption.POPULAR -> list.sortedByDescending { it.reviewCount }
        }
    }

    fun getTotalFilteredPages(): Int {
        val totalItems = getFilteredProducts().size
        if (totalItems == 0) return 1
        return (totalItems + pageSize - 1) / pageSize
    }

    fun getPaginatedFilteredProducts(): List<Product> {
        val all = getFilteredProducts()
        if (all.isEmpty()) return emptyList()
        val totalPages = getTotalFilteredPages()
        val page = _catalogPage.value.coerceIn(1, totalPages)
        val fromIndex = (page - 1) * pageSize
        val toIndex = (fromIndex + pageSize).coerceAtMost(all.size)
        if (fromIndex >= all.size) return emptyList()
        return all.subList(fromIndex, toIndex)
    }
}

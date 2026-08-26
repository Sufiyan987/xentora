package com.example.model

import androidx.annotation.DrawableRes

data class Product(
    val id: String,
    val name: String,
    val series: String,
    val tagline: String,
    val description: String,
    val price: Double,
    val originalPrice: Double? = null,
    val rating: Float,
    val reviewCount: Int,
    val category: ProductCategory,
    @DrawableRes val imageRes: Int,
    val inStock: Boolean = true,
    val stockCount: Int = 18,
    val tags: List<String> = emptyList(),
    val specs: Map<String, String> = emptyMap(),
    val availableColors: List<ProductColor> = listOf(
        ProductColor("Carbon Black", 0xFF14171C),
        ProductColor("Amber Gold", 0xFFE5A91A),
        ProductColor("Titanium Silver", 0xFF8C9BAE)
    ),
    val reviews: List<ProductReview> = emptyList()
)

enum class ProductCategory(val title: String, val iconName: String = "") {
    ALL("All (150+)", "grid"),
    AUDIO("Audio & Sound", "headphones"),
    WEARABLES("Timepieces & Wearables", "watch"),
    EDC_GEAR("EDC & Precision Tools", "build"),
    CARRY("Bags & Tech Carry", "backpack"),
    OPTICS("Smart Optics & Eyewear", "visibility"),
    DESK_STUDIO("Desk & Studio Setup", "desktop_windows"),
    POWER_CHARGING("Power & Fast Charging", "bolt"),
    TACTICAL("Tactical & Hardware", "shield"),
    CARBON_ACCESSORIES("Carbon & Leather", "wallet")
}

data class ProductColor(
    val name: String,
    val colorHex: Long
)

data class ProductReview(
    val id: String,
    val userName: String,
    val rating: Int,
    val date: String,
    val comment: String,
    val isVerified: Boolean = true
)

data class CartItem(
    val product: Product,
    val quantity: Int = 1,
    val selectedColor: ProductColor = product.availableColors.firstOrNull() ?: ProductColor("Default", 0xFF14171C)
) {
    val totalPrice: Double
        get() = product.price * quantity
}

data class Order(
    val id: String,
    val date: String,
    val items: List<CartItem>,
    val subtotal: Double,
    val discount: Double,
    val shipping: Double,
    val total: Double,
    val status: OrderStatus,
    val trackingNumber: String,
    val shippingAddress: String,
    val paymentMethod: String
)

enum class OrderStatus(val label: String) {
    PROCESSING("Processing"),
    SHIPPED("Shipped"),
    IN_TRANSIT("In Transit"),
    DELIVERED("Delivered")
}

package com.example.model

enum class MemberTier(val label: String, val badgeColorHex: Long, val discountPercent: Int, val perk: String) {
    FOUNDER("Founder Emerald VIP", 0xFF10B981, 20, "20% Off All Orders + Priority Express Courier"),
    TITANIUM("Titanium Tier", 0xFF8C9BAE, 15, "15% Off Flagships + Early Drops Access"),
    OBSIDIAN("Obsidian Member", 0xFF059669, 10, "10% Off + Free Lifetime Calibration"),
    MEMBER("Xentora Insider", 0xFF64748B, 5, "5% Off First Order + Member Perks")
}

data class SavedAddress(
    val id: String,
    val label: String,
    val recipientName: String,
    val street: String,
    val apartment: String? = null,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String = "United States",
    val isDefault: Boolean = false
) {
    val formatted: String
        get() = "$street${apartment?.let { ", $it" } ?: ""}, $city, $state $zipCode, $country"
}

data class SavedPaymentMethod(
    val id: String,
    val type: String, // "Google Pay", "Visa", "Mastercard", "Entora Vault Pay"
    val last4: String,
    val expiry: String,
    val cardHolder: String,
    val isDefault: Boolean = false
)

data class UserAccount(
    val id: String,
    val name: String,
    val email: String,
    val photoUrl: String? = null,
    val memberTier: MemberTier = MemberTier.FOUNDER,
    val goldRewardPoints: Int = 1850, // 1850 points = $18.50 discount
    val joinedDate: String = "Member since 2026",
    val phone: String = "+1 (555) 839-2041",
    val isGoogleAccount: Boolean = false,
    val savedAddresses: List<SavedAddress> = listOf(
        SavedAddress(
            id = "addr_1",
            label = "Primary Residence",
            recipientName = "Julian Vance",
            street = "742 Evergreen Terrace",
            apartment = "Penthouse 14B",
            city = "San Francisco",
            state = "CA",
            zipCode = "94107",
            country = "United States",
            isDefault = true
        ),
        SavedAddress(
            id = "addr_2",
            label = "Design Studio",
            recipientName = "Julian Vance",
            street = "450 Mission Street",
            apartment = "Suite 800",
            city = "San Francisco",
            state = "CA",
            zipCode = "94105",
            country = "United States",
            isDefault = false
        )
    ),
    val savedPaymentMethods: List<SavedPaymentMethod> = listOf(
        SavedPaymentMethod("pm_1", "Google Pay", "9012", "N/A", "Julian Vance", isDefault = true),
        SavedPaymentMethod("pm_2", "Visa Black Titanium", "4821", "08/29", "Julian Vance", isDefault = false),
        SavedPaymentMethod("pm_3", "Entora Vault Direct", "7733", "04/30", "Julian Vance", isDefault = false)
    )
)

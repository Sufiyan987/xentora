package com.example.data

import com.example.R
import com.example.model.Product
import com.example.model.ProductCategory
import com.example.model.ProductColor
import com.example.model.ProductReview

object EntoraRepository {

    private val defaultColors = listOf(
        ProductColor("Carbon Black", 0xFF14171C),
        ProductColor("Emerald Spec", 0xFF10B981),
        ProductColor("Titanium Silver", 0xFF8C9BAE)
    )

    private val audioColors = listOf(
        ProductColor("Matte Stealth Black", 0xFF14171C),
        ProductColor("Anodized Emerald Green", 0xFF10B981),
        ProductColor("Brushed Platinum", 0xFFBAC7D5),
        ProductColor("Midnight Navy", 0xFF1B2430)
    )

    private val edcColors = listOf(
        ProductColor("Raw Titanium Ti-6Al-4V", 0xFF8C9BAE),
        ProductColor("DLC Diamond Carbon", 0xFF14171C),
        ProductColor("Emerald Anodized Spec", 0xFF10B981),
        ProductColor("Stonewashed Steel", 0xFF4A5568)
    )

    // Base Flagship definitions
    private val flagshipProducts: List<Product> = listOf(
        Product(
            id = "entora-apex-h1",
            name = "ENTORA Apex H1 Pro",
            series = "APEX SERIES",
            tagline = "Hybrid ANC Carbon Wireless Studio Headphones",
            description = "Crafted with custom 45mm neodymium drivers, active noise cancellation tuned to -42dB, ultra-plush memory foam earpads, and CNC-machined gold amber pivot hinges. Delivers 60 hours of uncompromised hi-res wireless sound.",
            price = 299.99,
            originalPrice = 349.99,
            rating = 4.9f,
            reviewCount = 142,
            category = ProductCategory.AUDIO,
            imageRes = R.drawable.img_product_audio,
            inStock = true,
            stockCount = 24,
            tags = listOf("Flagship", "Hi-Res Audio", "Best Seller"),
            specs = mapOf(
                "Driver Size" to "45mm Custom Neodymium",
                "Battery Life" to "60 Hours (ANC On: 45h)",
                "Connectivity" to "Bluetooth 5.4 / Lossless USB-C",
                "ANC Depth" to "-42 dB Active Hybrid",
                "Weight" to "268 grams",
                "Fast Charge" to "10 min = 6 hours playback"
            ),
            availableColors = audioColors,
            reviews = listOf(
                ProductReview(
                    id = "r1",
                    userName = "Julian Vance",
                    rating = 5,
                    date = "August 18, 2026",
                    comment = "The acoustics are unmatched. The gold amber metallic accents and matte carbon finish feel insanely luxurious.",
                    isVerified = true
                ),
                ProductReview(
                    id = "r2",
                    userName = "Elena Rostova",
                    rating = 5,
                    date = "August 12, 2026",
                    comment = "Best ANC I've experienced on long flights. Battery easily lasted through 4 days of heavy work.",
                    isVerified = true
                )
            )
        ),
        Product(
            id = "entora-chrono-x",
            name = "ENTORA Chrono-X Titanium",
            series = "VECTOR SERIES",
            tagline = "Grade 5 Titanium AMOLED Smartwatch",
            description = "Aerospace grade 5 titanium chassis, sapphire crystal display, continuous bio-metric telemetry (ECG, SpO2, HRV), and dynamic gold ambient watchfaces. Water resistant to 100 meters with 14-day battery reserve.",
            price = 449.00,
            originalPrice = 499.00,
            rating = 4.8f,
            reviewCount = 98,
            category = ProductCategory.WEARABLES,
            imageRes = R.drawable.img_product_watch,
            inStock = true,
            stockCount = 12,
            tags = listOf("Titanium", "Sapphire Glass", "100m Water"),
            specs = mapOf(
                "Chassis" to "Grade 5 Titanium + Carbon Bezel",
                "Display" to "1.43-inch AMOLED 1000 nits",
                "Battery Life" to "14 Days Typical Use",
                "Water Resistance" to "10 ATM (100m)",
                "Sensors" to "ECG, SpO2, Optical Heart, Temp",
                "Glass" to "Mohs 9 Sapphire Crystal"
            ),
            availableColors = defaultColors,
            reviews = listOf(
                ProductReview(
                    id = "r3",
                    userName = "Marcus Sterling",
                    rating = 5,
                    date = "August 15, 2026",
                    comment = "Feels like a luxury mechanical watch with cutting-edge sensors. The golden amber display is breathtaking.",
                    isVerified = true
                )
            )
        ),
        Product(
            id = "entora-matrix-edc",
            name = "ENTORA Matrix Precision EDC Tool",
            series = "PRECISION SERIES",
            tagline = "24-in-1 Skeletonized Titanium Multi-Gear",
            description = "Precision CNC machined from single-block titanium with gold anodized accents. Features quick-deploy hardened carbon steel blade, replaceable magnetic bit driver, pry edge, bottle opener, and metric calipers.",
            price = 129.50,
            originalPrice = 149.00,
            rating = 4.9f,
            reviewCount = 85,
            category = ProductCategory.EDC_GEAR,
            imageRes = R.drawable.img_product_gear,
            inStock = true,
            stockCount = 30,
            tags = listOf("CNC Machined", "Lifetime Warranty", "Ultra-Compact"),
            specs = mapOf(
                "Material" to "Ti-6Al-4V Titanium + Gold Anodized",
                "Blade Steel" to "CPM S35VN Stainless",
                "Bits Included" to "6 Double-Ended Magnetic Bits",
                "Weight" to "92 grams",
                "Length" to "88mm Closed",
                "Lock Type" to "Precision Frame Lock"
            ),
            availableColors = edcColors,
            reviews = listOf(
                ProductReview(
                    id = "r4",
                    userName = "Alexei K.",
                    rating = 5,
                    date = "August 10, 2026",
                    comment = "Tolerances are laser-precise. The gold accents set it apart from standard dull EDC tools.",
                    isVerified = true
                )
            )
        ),
        Product(
            id = "entora-nomad-backpack",
            name = "ENTORA Nomad X-Pack 28L",
            series = "CARRY SERIES",
            tagline = "Waterproof Cordura Modular Tech Commuter",
            description = "Engineered with 1000D ballistic Cordura, Fidlock magnetic gold anodized sternum buckles, dedicated 16-inch suspended laptop vault, TSA flat-opening compartment, and hidden passport RFID pocket.",
            price = 189.00,
            originalPrice = 219.00,
            rating = 4.7f,
            reviewCount = 112,
            category = ProductCategory.CARRY,
            imageRes = R.drawable.img_product_bag,
            inStock = true,
            stockCount = 19,
            tags = listOf("Waterproof", "Fidlock Magnetic", "28 Liters"),
            specs = mapOf(
                "Capacity" to "28 Liters Expandable to 32L",
                "Fabric" to "1000D Cordura + YKK Aquaguard",
                "Laptop Sleeve" to "Up to 16-inch MacBook Pro",
                "Buckles" to "Fidlock V-Buckle Anodized Gold",
                "Weight" to "1.18 kg",
                "Dimensions" to "50 x 32 x 18 cm"
            ),
            availableColors = defaultColors,
            reviews = listOf(
                ProductReview(
                    id = "r5",
                    userName = "Samantha Reed",
                    rating = 5,
                    date = "August 05, 2026",
                    comment = "Best commuter backpack I've ever owned. The Fidlock buckles are super satisfying and ergonomic.",
                    isVerified = true
                )
            )
        ),
        Product(
            id = "entora-solaris-optics",
            name = "ENTORA Solaris Smart Audio Optics",
            series = "OPTICS SERIES",
            tagline = "Polarized Amber UV400 Open-Ear Audio Eyewear",
            description = "Ultra-lightweight TR90 frame with titanium flex hinges, Zeiss polarized amber-tinted lenses, and dual directional micro-speakers providing private high-fidelity spatial audio without obstructing ambient awareness.",
            price = 229.00,
            originalPrice = 259.00,
            rating = 4.8f,
            reviewCount = 64,
            category = ProductCategory.OPTICS,
            imageRes = R.drawable.img_product_glasses,
            inStock = true,
            stockCount = 15,
            tags = listOf("Zeiss Optics", "Open-Ear Spatial", "UV400 Polarized"),
            specs = mapOf(
                "Lens" to "Zeiss Amber Polarized UV400",
                "Audio" to "Dual Directional Open-Ear Drivers",
                "Battery Life" to "8 Hours Continuous Playback",
                "Frame Material" to "TR90 Memory Polymer + Titanium",
                "Water Protection" to "IPX4 Sweat & Splash Resistant",
                "Microphones" to "Dual Beamforming with Wind Shield"
            ),
            availableColors = defaultColors,
            reviews = listOf(
                ProductReview(
                    id = "r6",
                    userName = "Dominic Thorne",
                    rating = 5,
                    date = "August 14, 2026",
                    comment = "Audio is shockingly crisp and clear while walking in the city, and the amber lenses enhance contrast brilliantly.",
                    isVerified = true
                )
            )
        )
    )

    // Generator for 160+ unique, highly detailed ENTORA products
    private fun generateExtendedCatalog(): List<Product> {
        val list = mutableListOf<Product>()
        list.addAll(flagshipProducts)

        val categories = listOf(
            ProductCategory.AUDIO to R.drawable.img_product_audio,
            ProductCategory.WEARABLES to R.drawable.img_product_watch,
            ProductCategory.EDC_GEAR to R.drawable.img_product_gear,
            ProductCategory.CARRY to R.drawable.img_product_bag,
            ProductCategory.OPTICS to R.drawable.img_product_glasses,
            ProductCategory.DESK_STUDIO to R.drawable.img_product_audio,
            ProductCategory.POWER_CHARGING to R.drawable.img_product_gear,
            ProductCategory.TACTICAL to R.drawable.img_product_gear,
            ProductCategory.CARBON_ACCESSORIES to R.drawable.img_product_bag
        )

        val audioItems = listOf(
            Triple("ENTORA Pulse Earbuds Pro", "Spatial Audio Wireless ANC In-Ear", 179.00),
            Triple("ENTORA SoundCore Studio Sub", "Dual Bass Radiator Reference Woofer", 349.00),
            Triple("ENTORA Vibe Open-Fit Clip", "Ear-Cuff Lossless Conduction Audio", 139.00),
            Triple("ENTORA Studio DAC Pro", "32-bit/768kHz Quad MQA Desktop Amp", 279.00),
            Triple("ENTORA StreamMic Precision", "Cardioid Gold-Capsule Broadcast Mic", 199.00),
            Triple("ENTORA Carbon Acoustic Mon", "Nearfield Active Studio Monitors (Pair)", 499.00),
            Triple("ENTORA Auris Audiophile Cable", "Silver-Plated Monocrystalline 4.4mm", 89.00),
            Triple("ENTORA SoundShield Boom Mic", "Detachable Noise-Rejecting Boom Headset", 69.00),
            Triple("ENTORA Horizon Soundbar", "Dolby Atmos 5.1 Carbon Soundbase", 599.00),
            Triple("ENTORA Nova Wireless Speaker", "360-Degree Spatial Sound Amber Luminary", 219.00),
            Triple("ENTORA BassLink Sub-Module", "Wireless Zero-Latency Acoustic Transmitter", 119.00),
            Triple("ENTORA Apex Travel Case Gold", "Hardshell Carbon Fiber Earcup Vault", 49.00),
            Triple("ENTORA Hi-Res Dongle DAC", "Dual Cirrus Logic USB-C to 3.5mm Hi-Fi", 79.00),
            Triple("ENTORA Memory Foam Ear Cushions", "Cooling Gel Infused Gold Stitched Pads", 39.00),
            Triple("ENTORA PureSound Desktop Pods", "Balanced Armature Desktop Reference", 249.00),
            Triple("ENTORA Audio Hub Matrix", "Multi-Source Optical & BT Switcher", 159.00),
            Triple("ENTORA Velvet Acoustic Shield", "Desktop Isolation Acoustic Booth", 189.00),
            Triple("ENTORA Waveform Field Recorder", "32-Bit Float 4-Track Sound Studio", 329.00)
        )

        val wearablesItems = listOf(
            Triple("ENTORA Chrono-Steel Classic", "Stainless Steel Ceramic Bezel Smartwatch", 329.00),
            Triple("ENTORA Aura Bio-Ring", "Titanium Sleep & Recovery Smart Ring", 269.00),
            Triple("ENTORA Terra GPS Expedition", "Solar-Assisted Dual-Freq Navigation Watch", 549.00),
            Triple("ENTORA Aero Carbon Strap", "Breathable FKM Rubber & Carbon Clasp", 59.00),
            Triple("ENTORA Milanese Gold Mesh", "Magnetic Stainless Steel Weave Band", 79.00),
            Triple("ENTORA Horology Link Band", "Grade 5 Titanium Solid Link Bracelet", 149.00),
            Triple("ENTORA BioTrack Fitness Armband", "Optical Pulse Heart Rate Telemetry", 89.00),
            Triple("ENTORA Chrono Wireless Dock", "Weighted CNC Amber Charging Monolith", 65.00),
            Triple("ENTORA Quantum Oxygen Tracker", "Continuous Medical-Grade SpO2 Monitor", 119.00),
            Triple("ENTORA Alpine Ballistic Strap", "Reinforced Kevlar Quick-Release Band", 45.00),
            Triple("ENTORA Sapphire Screen Shield", "Edge-to-Edge Mohs 9 Curved Protector", 25.00),
            Triple("ENTORA Luxe Horology Travel Roll", "Full Grain Leather 3-Watch Vault", 129.00),
            Triple("ENTORA Pulse Hybrid Dial", "Mechanical Hands with Hidden OLED", 389.00),
            Triple("ENTORA Chrono Dive 300M", "Helium Valve Professional Diver Watch", 680.00),
            Triple("ENTORA Minimalist Leather Band", "Horween Vegetable-Tanned Amber Stitch", 69.00),
            Triple("ENTORA Apex Fit Smart Scale", "Dual-Frequency Segmental Body Composition", 109.00),
            Triple("ENTORA Smart Bio-Patch 3-Pack", "Continuous Glucose & Hydration Sensor", 99.00),
            Triple("ENTORA Vector Travel Charger", "Folding Dual Watch & Phone Magsafe Pad", 85.00)
        )

        val edcItems = listOf(
            Triple("ENTORA Titanium Bolt Pen", "CNC Machined Schmidt EasyFlow Pen", 89.00),
            Triple("ENTORA PryBar Micro X", "Grade 5 Titanium Pocket Utility Lever", 49.00),
            Triple("ENTORA Lumen-X Pocket Torch", "1200 Lumens Quad-Core Amber Flashlight", 95.00),
            Triple("ENTORA BitVault Precision Set", "24-Piece S2 Hardened Magnetic Bit Case", 59.00),
            Triple("ENTORA KeyMatrix Organizer", "Carbon Fiber Key Bar with Pocket Clip", 42.00),
            Triple("ENTORA Razor Edge Craft Knife", "Scalpel Blade Titanium Folding Cutter", 64.00),
            Triple("ENTORA Tactix Carabiner Lock", "Gate-Locking High Load Titanium Clip", 38.00),
            Triple("ENTORA Micro Capsule Stash", "O-Ring Sealed Waterproof Titanium Pod", 29.00),
            Triple("ENTORA Cyber Caliper Rule", "Pocket Dual Metric/Imperial Titanium Scale", 34.00),
            Triple("ENTORA Spinner Precision Fidget", "Ceramic Hybrid Bearing Heavy Brass Core", 55.00),
            Triple("ENTORA Multi-Angle Pry Tool", "Nail Puller, Hex Wrench & Oxygen Wrench", 52.00),
            Triple("ENTORA Damascus Pocket Folder", "Layered Damascus Blade Titanium Handle", 210.00),
            Triple("ENTORA Magnetic Quick Disconnect", "20kg Neodymium Key Coupler", 28.00),
            Triple("ENTORA Utility Clip Money Clamp", "Carbon Spring Steel Slim Bifold Clip", 32.00),
            Triple("ENTORA Pocket Level & Angle Gauge", "Liquid Filled Bubble High Visibility", 24.00),
            Triple("ENTORA Armor Lighter Torch", "Windproof Jet Flame Brass & Titanium Case", 78.00),
            Triple("ENTORA Titanium Chopsticks Set", "Collapsible Ultralight Dining EDC", 45.00),
            Triple("ENTORA Whistle Sound Defense", "120dB Pealess Titanium Emergency Siren", 26.00)
        )

        val carryItems = listOf(
            Triple("ENTORA Sling Modular 5L", "Weatherproof X-Pac Crossbody Bag", 98.00),
            Triple("ENTORA Tech Pouch Organizer", "Origami Accordion Cable Storage Vault", 59.00),
            Triple("ENTORA Duffel Voyager 45L", "Ballistic Cordura Weekender Travel Bag", 229.00),
            Triple("ENTORA Briefcase Exec 15L", "Structured Carbon Hybrid Work Bag", 195.00),
            Triple("ENTORA Camera Cube Insert", "Padded Fleece Modular Lens Divider", 65.00),
            Triple("ENTORA Passport Wallet RFID", "Top-Grain Leather Travel Document Folio", 75.00),
            Triple("ENTORA Hip Pack Compact 2L", "Ultra-Lightweight Dyneema Waist Pack", 68.00),
            Triple("ENTORA Roll-Top Dry Bag 20L", "IPX7 Submersible Waterproof Sack", 48.00),
            Triple("ENTORA Laptop Sleeve Armor 16", "Shock-Absorbing Memory Foam Case", 54.00),
            Triple("ENTORA Shoe Bag Ventilated", "Anti-Odor Ripstop Travel Shoe Pouch", 32.00),
            Triple("ENTORA Packing Cubes 4-Set", "Compression Mesh Modular Luggage Cubes", 49.00),
            Triple("ENTORA Luggage Tag Titanium", "Laser-Etched Privacy QR ID Tag", 22.00),
            Triple("ENTORA Modular Strap Pad", "Air-Mesh Shoulder Comfort Extender", 25.00),
            Triple("ENTORA Modular Bottle Pouch", "Insulated Thermal Cinch Bottle Holster", 35.00),
            Triple("ENTORA Field Tote 24L", "Heavy Duty Waxed Canvas Studio Tote", 115.00),
            Triple("ENTORA Hardcase Suitcase 38L", "Aviation Grade Aluminum Carry-On", 480.00),
            Triple("ENTORA Key Leash Retractable", "Dyneema Cord Quick-Snap Tether", 28.00),
            Triple("ENTORA Rain Fly Cover", "High-Visibility Reflective Backpack Shell", 29.00)
        )

        val opticsItems = listOf(
            Triple("ENTORA Horizon Polarized", "Matte Black Acetate Amber Gradient Lenses", 169.00),
            Triple("ENTORA Aviator Vector Titanium", "Double Bridge Ultra-Light Sunglasses", 199.00),
            Triple("ENTORA BlueBlock Studio Lenses", "Zero Distortion 450nm Screen Glasses", 119.00),
            Triple("ENTORA Shadow Wrap Tactical", "Ballistic Impact Rated Eye Shield", 149.00),
            Triple("ENTORA Wayfarer Audio Gen 2", "Smart Audio Touch-Bar Polarized Shades", 249.00),
            Triple("ENTORA Vision Monocle 10x", "Optical Grade Precision Pocket Magnifier", 59.00),
            Triple("ENTORA Microfiber Care Kit", "Anti-Fog Lens Solution & Carbon Cloth", 24.00),
            Triple("ENTORA Leather Eyewear Sleeve", "Magnetic Fold-Flat Hard Protection Case", 45.00),
            Triple("ENTORA Prism Sport Goggles", "Photochromic Cycling & Trail Eyewear", 189.00),
            Triple("ENTORA Titan Rimless Eyeglasses", "12-Gram Beta-Titanium Minimal Frames", 219.00),
            Triple("ENTORA Gold Mirror Pilot", "24K Gold Flash Coated Mineral Glass", 239.00),
            Triple("ENTORA Night Drive Optics", "High-Contrast Anti-Glare Amber Lenses", 139.00),
            Triple("ENTORA Floating Eyewear Strap", "Neoprene Gold Accent Security Retainer", 18.00),
            Triple("ENTORA AR HUD Display Glass", "Developer Edition Waveguide Smart Glass", 699.00),
            Triple("ENTORA Studio Shield Readers", "+1.5 to +3.0 Blue Light Magnification", 89.00),
            Triple("ENTORA Glare-Zero Clip-On", "Spring Loaded Polarized Titanium Clip", 55.00),
            Triple("ENTORA Optics Cleaning Pen", "Carbon Micro-Pad Lens Dust Remover", 19.00),
            Triple("ENTORA Cyber Visor Pro", "Full-Face UV400 Reflective Aerodynamic Shield", 129.00)
        )

        val deskItems = listOf(
            Triple("ENTORA Desk Mat Wool & Leather", "90x40cm Merino Wool Felt & Saddle Leather", 79.00),
            Triple("ENTORA Monitor Lightbar Pro", "Wireless Dial CRI 97 Asymmetric Lamp", 129.00),
            Triple("ENTORA CNC Aluminum Laptop Stand", "Elevated Ergonomic Heat Dissipation Riser", 69.00),
            Triple("ENTORA MagBase Headphone Stand", "Solid Walnut & Anodized Gold Earpad Rest", 89.00),
            Triple("ENTORA Cyber Mechanical Keyboard", "Gasket Mount CNC Aluminum Wireless 75%", 259.00),
            Triple("ENTORA Precision Optical Mouse", "4K Polling Carbon Fiber Wireless Mouse", 139.00),
            Triple("ENTORA Magnetic Cable Block 3-Pack", "Solid Weighted Brass Desk Cable Guides", 39.00),
            Triple("ENTORA Desktop Audio Interface", "XLR Combo + 48V Phantom Dual Channel", 179.00),
            Triple("ENTORA Wireless Charging Coaster", "15W Fast Qi Ceramic Desktop Pad", 49.00),
            Triple("ENTORA Studio Acoustic Panels (6)", "Hexagonal High-Density Sound Dampers", 99.00),
            Triple("ENTORA Monitor Arm Single VESA", "Heavy-Duty Gas Spring Aluminum Arm", 149.00),
            Triple("ENTORA Under-Desk Cable Tray", "Steel Mesh Magnetic Routing Channel", 45.00),
            Triple("ENTORA Footrest Ergonomic Rocker", "Memory Foam Solid Bamboo Base", 59.00),
            Triple("ENTORA Studio Monitor Isolation Pads", "Acoustic Foam Angle Decoupling Riser", 35.00),
            Triple("ENTORA Pen Tray & Organizer", "CNC Milled Anodized Black & Gold Dish", 48.00),
            Triple("ENTORA Desk Clamping Power Hub", "4x AC Outlets + 3x 100W USB-C Ports", 85.00),
            Triple("ENTORA Minimalist Desk Clock", "VFD Tube Style Ambient Desktop Clock", 119.00),
            Triple("ENTORA Carbon Fiber Mousepad", "Speed Micro-Texture Hard Gaming Surface", 52.00)
        )

        val powerItems = listOf(
            Triple("ENTORA PowerCore 25000 140W", "Dual USB-C PD 3.1 Airline Safe Power Bank", 149.00),
            Triple("ENTORA MagSafe Slim Pack 10K", "15W Magnetic Wireless Pocket Battery", 69.00),
            Triple("ENTORA GaN 100W Wall Charger", "Foldable 4-Port Fast Travel Brick", 59.00),
            Triple("ENTORA Braided Kevlar Cable 240W", "2-Meter 40Gbps USB4 USB-C to USB-C", 39.00),
            Triple("ENTORA Desktop Power Station 200W", "6-Port Intelligent Display Charger", 119.00),
            Triple("ENTORA Solar Folding Panel 45W", "Dual USB + DC Out Outdoor Charger", 129.00),
            Triple("ENTORA Magnetic Car Wireless Mount", "15W Active Cryo-Cooling MagSafe Vent", 55.00),
            Triple("ENTORA Emergency Crank Power 5K", "Hand-Crank LED Siren Power Generator", 49.00),
            Triple("ENTORA 3-in-1 Travel MagFold", "Simultaneous Phone, Watch & Buds Charger", 89.00),
            Triple("ENTORA USB-C Voltage Power Meter", "OLED Color Realtime Wattage Tester", 32.00),
            Triple("ENTORA Gold-Plated Lightning Cable", "MFi Certified Braided Fast Charge Cable", 28.00),
            Triple("ENTORA Universal Travel Adapter", "200+ Countries 65W GaN Integrated", 49.00),
            Triple("ENTORA Power Strip Cube 65W", "3 Outlets + 3 USB-C Compact Desktop Block", 59.00),
            Triple("ENTORA Waterproof Battery Box", "IP68 Submersible Rugged Power Storage", 79.00),
            Triple("ENTORA 12V Car Super Charger", "Dual 65W USB-C Quick Metal Adapter", 34.00),
            Triple("ENTORA Watch Pod Keychain Charger", "Magnetic USB-C Portable Watch Dongle", 25.00),
            Triple("ENTORA Heavy Duty Extension 3M", "Reinforced Industrial 15A Braided Cord", 38.00),
            Triple("ENTORA Wireless Charging Phone Grip", "Snap-On MagSafe Power Bank & Kickstand", 49.00)
        )

        val tacticalItems = listOf(
            Triple("ENTORA Tactical Pen Strike Tip", "Tungsten Glass Breaker Carbon Defense", 59.00),
            Triple("ENTORA Ferro Rod Fire Starter", "Heavy-Duty Magnesium Strikemaster Tool", 34.00),
            Triple("ENTORA Survival Multi-Axe", "Skeletonized 440C Stainless Tomahawk", 139.00),
            Triple("ENTORA Paracord 550 Spool 100M", "7-Strand Core Reflective Gold Spec", 22.00),
            Triple("ENTORA Tactical Belt Cobra Buckle", "AustriAlpin Quick Release Ballistic Belt", 75.00),
            Triple("ENTORA Trauma First Aid Kit", "Compact Molle Tourniquet Trauma Pouch", 89.00),
            Triple("ENTORA Night Glow Marker Beads", "Tritium-Free Photoluminescent Lanyard", 19.00),
            Triple("ENTORA Signal Mirror Rescue", "Sighting Hole Floating Aviation Grade", 24.00),
            Triple("ENTORA Waterproof Field Notebook", "All-Weather Synthetic Stone Paper 3-Pack", 25.00),
            Triple("ENTORA Tactical Sheath Kydex", "Multi-Position Belt Mount Tool Holder", 39.00),
            Triple("ENTORA Armor Plate Carrier Pouch", "Laser-Cut Cordura Admin Utility Rig", 69.00),
            Triple("ENTORA Heavy Grip Tactical Gloves", "Kevlar Knuckle Touchscreen Mechanics", 55.00),
            Triple("ENTORA Compact Binoculars 10x25", "Bak-4 Prism Waterproof Rubber Armored", 99.00),
            Triple("ENTORA High Decibel Strobe Beacon", "Emergency Blue & Gold Flare Light", 48.00),
            Triple("ENTORA Camo Wrap Self-Adhesive", "Rechargeable Tactical Gear Grip Tape", 16.00),
            Triple("ENTORA Pocket Wire Saw Heavy Duty", "High-Carbon Steel Emergency Limb Cutter", 18.00),
            Triple("ENTORA Magnetic Tool Holster", "Neodymium Clip-On Quick Retention", 29.00),
            Triple("ENTORA Titanium Spork Survival", "Integrated Bottle Opener & Hex Key", 22.00)
        )

        val carbonItems = listOf(
            Triple("ENTORA Carbon Card Wallet", "RFID-Blocking Titanium Spring Cardholder", 69.00),
            Triple("ENTORA Carbon Fiber Key FOB Case", "Universal Smart Key Shield Protector", 35.00),
            Triple("ENTORA Full Carbon Money Clip", "Ultra-High Modulus Gloss Carbon Fiber", 42.00),
            Triple("ENTORA Carbon Luggage Tag", "Genuine Twill Weave Luggage ID Plate", 28.00),
            Triple("ENTORA Carbon Coaster Set (4)", "Forged Carbon Hexagonal Table Armor", 49.00),
            Triple("ENTORA Carbon Cigar Case 3-Stick", "Airtight Telescoping Carbon Cylinder", 89.00),
            Triple("ENTORA Carbon Fiber Flask 8oz", "Titanium Lined Ultralight Liquor Vessel", 119.00),
            Triple("ENTORA Carbon Passport Cover", "Leather Lined RFID Bi-Fold Passport Case", 65.00),
            Triple("ENTORA Carbon Phone Case Slim", "0.6mm Aramid Fiber Matte Drop Protection", 49.00),
            Triple("ENTORA Carbon Valet Desk Tray", "Curved Edge Forged Carbon Catchall", 79.00),
            Triple("ENTORA Carbon Watch Box 2-Slot", "Cushioned Alcantara Display Case", 139.00),
            Triple("ENTORA Leather Card Sleeve", "Vegetable Tanned Leather Pull-Tab Wallet", 52.00),
            Triple("ENTORA Carbon Keychain Ring", "CNC Machined Quick-Release Key Loop", 24.00),
            Triple("ENTORA Carbon Fiber Pen Holder", "Weighted Desktop Minimal Cylinder", 38.00),
            Triple("ENTORA Carbon Shoe Horn Pocket", "Ergonomic Polished Heel Glide EDC", 29.00),
            Triple("ENTORA Forged Carbon Ring Band", "Comfort Fit Inlaid Amber Gold Stripe", 149.00),
            Triple("ENTORA Carbon Bookmark Luxury", "Laser-Cut Micro-Twill Reading Accent", 15.00),
            Triple("ENTORA Carbon License Plate Frame", "Pure 3K Twill Automotive Exterior Frame", 45.00)
        )

        val categoryDataMap = mapOf(
            ProductCategory.AUDIO to (audioItems to R.drawable.img_product_audio),
            ProductCategory.WEARABLES to (wearablesItems to R.drawable.img_product_watch),
            ProductCategory.EDC_GEAR to (edcItems to R.drawable.img_product_gear),
            ProductCategory.CARRY to (carryItems to R.drawable.img_product_bag),
            ProductCategory.OPTICS to (opticsItems to R.drawable.img_product_glasses),
            ProductCategory.DESK_STUDIO to (deskItems to R.drawable.img_product_audio),
            ProductCategory.POWER_CHARGING to (powerItems to R.drawable.img_product_gear),
            ProductCategory.TACTICAL to (tacticalItems to R.drawable.img_product_gear),
            ProductCategory.CARBON_ACCESSORIES to (carbonItems to R.drawable.img_product_bag)
        )

        var counter = 101
        for ((cat, pair) in categoryDataMap) {
            val (items, imgRes) = pair
            for (item in items) {
                val (name, tagline, price) = item
                val originalPrice = (price * 1.2).toInt().toDouble()
                val rating = 4.6f + ((counter % 5) * 0.08f)
                val reviewCount = 28 + (counter % 120)
                val inStock = counter % 13 != 0
                val stockCount = if (inStock) (8 + (counter % 35)) else 0

                val id = "entora-${cat.name.lowercase()}-$counter"
                val series = when (cat) {
                    ProductCategory.AUDIO -> "ACOUSTIC SERIES"
                    ProductCategory.WEARABLES -> "CHRONO SERIES"
                    ProductCategory.EDC_GEAR -> "PRECISION LABS"
                    ProductCategory.CARRY -> "NOMAD CARRY"
                    ProductCategory.OPTICS -> "SPECTRUM OPTICS"
                    ProductCategory.DESK_STUDIO -> "STUDIO WORKSPACE"
                    ProductCategory.POWER_CHARGING -> "ENERGETIX"
                    ProductCategory.TACTICAL -> "TACTIX HARDWARE"
                    ProductCategory.CARBON_ACCESSORIES -> "CARBON VAULT"
                    else -> "ENTORA CORE"
                }

                val tags = mutableListOf<String>()
                if (price > 200) tags.add("Premium")
                if (counter % 4 == 0) tags.add("Best Seller")
                if (counter % 5 == 0) tags.add("New Release")
                if (counter % 3 == 0) tags.add("Titanium")
                if (tags.isEmpty()) tags.add("Featured")

                list.add(
                    Product(
                        id = id,
                        name = name,
                        series = series,
                        tagline = tagline,
                        description = "Engineered with ENTORA's signature aerospace craftsmanship. Features precision gold amber accents, lightweight structural alloy, and undergoes exhaustive quality benchmark validation. Designed for discerning professionals and tech connoisseurs.",
                        price = price,
                        originalPrice = originalPrice,
                        rating = rating.coerceAtMost(5.0f),
                        reviewCount = reviewCount,
                        category = cat,
                        imageRes = imgRes,
                        inStock = inStock,
                        stockCount = stockCount,
                        tags = tags,
                        specs = mapOf(
                            "Material" to "Aerospace Alloy / Grade 5 Ti",
                            "Finish" to "Anodized Amber & Stealth Carbon",
                            "Warranty" to "ENTORA 3-Year Global Protection",
                            "Origin" to "Precision Engineered & Assembled",
                            "Authenticity" to "Laser-Etched Serial & Holographic Seal"
                        ),
                        availableColors = defaultColors,
                        reviews = listOf(
                            ProductReview(
                                id = "rev_$counter",
                                userName = "Verified Entora Owner",
                                rating = 5,
                                date = "August 2026",
                                comment = "The build quality is exceptional. You can immediately feel the precision in the hand.",
                                isVerified = true
                            )
                        )
                    )
                )
                counter++
            }
        }

        return list
    }

    val sampleProducts: List<Product> by lazy {
        generateExtendedCatalog()
    }
}

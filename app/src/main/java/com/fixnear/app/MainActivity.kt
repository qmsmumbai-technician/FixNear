package com.fixnear.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {

    private val blue = Color.rgb(20, 79, 180)
    private val dark = Color.rgb(25, 32, 45)
    private val orange = Color.rgb(255, 75, 35)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()

    private fun roundedBackground(
        color: Int,
        radius: Int = 18,
        strokeColor: Int? = null
    ): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = dp(radius).toFloat()
            strokeColor?.let { setStroke(dp(1), it) }
        }
    }

    private fun text(
        value: String,
        size: Float,
        color: Int = dark,
        bold: Boolean = false
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            gravity = Gravity.CENTER_VERTICAL
            if (bold) typeface = Typeface.DEFAULT_BOLD
        }
    }

    private fun showHome() {

        val scroll = ScrollView(this).apply {
            setBackgroundColor(Color.rgb(250, 251, 253))
        }

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(12), dp(18), dp(18))
        }

        scroll.addView(root)

        // ---------------- HEADER ----------------

        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        val menu = text("☰", 30f, dark)
        header.addView(
            menu,
            LinearLayout.LayoutParams(dp(55), dp(55))
        )

        val logoBox = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }

        val logo = TextView(this).apply {
            text = "FixNear"
            textSize = 34f
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
        }

        val logoText = android.text.SpannableString("FixNear")
        logoText.setSpan(
            android.text.style.ForegroundColorSpan(blue),
            0, 3,
            android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        logoText.setSpan(
            android.text.style.ForegroundColorSpan(orange),
            3, 7,
            android.text.Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        logo.text = logoText

        logoBox.addView(logo)

        val subtitle = text(
            "A technician is just a tap away.",
            13f,
            Color.GRAY
        )
        subtitle.gravity = Gravity.CENTER
        logoBox.addView(subtitle)

        header.addView(
            logoBox,
            LinearLayout.LayoutParams(0, dp(65), 1f)
        )

        val notification = TextView(this).apply {
            text = "🔔"
            textSize = 27f
            gravity = Gravity.CENTER
        }

        header.addView(
            notification,
            LinearLayout.LayoutParams(dp(55), dp(55))
        )

        root.addView(header)

        // ---------------- LOCATION ----------------

        val location = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(14), dp(8), dp(14), dp(8))
            background = roundedBackground(Color.WHITE, 20)
            elevation = dp(3).toFloat()
        }

        val pin = text("📍", 27f)
        location.addView(
            pin,
            LinearLayout.LayoutParams(dp(45), dp(60))
        )

        val locationText = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        locationText.addView(
            text("Your Location", 12f, Color.GRAY, false)
        )

        locationText.addView(
            text(
                "Andheri West, Mumbai, Maharashtra",
                16f,
                dark,
                true
            )
        )

        location.addView(
            locationText,
            LinearLayout.LayoutParams(0, dp(60), 1f)
        )

        val arrow = text("⌄", 27f)
        location.addView(
            arrow,
            LinearLayout.LayoutParams(dp(35), dp(60))
        )

        root.addView(
            location,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(76)
            ).apply {
                setMargins(0, dp(10), 0, dp(14))
            }
        )

        // ---------------- TRUST BANNER ----------------

        val banner = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(18), dp(14), dp(14), dp(14))
            background = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.rgb(12, 73, 170),
                    Color.rgb(25, 102, 205)
                )
            ).apply {
                cornerRadius = dp(20).toFloat()
            }
        }

        val bannerText = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        bannerText.addView(
            text(
                "Trusted Technicians",
                21f,
                Color.WHITE,
                true
            )
        )

        bannerText.addView(
            text(
                "Verified & Rated",
                20f,
                Color.WHITE,
                true
            )
        )

        bannerText.addView(
            text(
                "Fast. Reliable. Affordable.",
                15f,
                Color.WHITE
            )
        )

        bannerText.addView(
            text(
                "👤 👩 👨   ⭐ 10K+",
                15f,
                Color.WHITE,
                true
            )
        )

        bannerText.addView(
            text(
                "Happy Customers",
                13f,
                Color.WHITE
            )
        )

        banner.addView(
            bannerText,
            LinearLayout.LayoutParams(0, dp(190), 1f)
        )

        val technician = TextView(this).apply {
            text = "👨‍🔧"
            textSize = 72f
            gravity = Gravity.CENTER
        }

        banner.addView(
            technician,
            LinearLayout.LayoutParams(dp(125), dp(170))
        )

        root.addView(
            banner,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(190)
            ).apply {
                setMargins(0, 0, 0, dp(18))
            }
        )

        // ---------------- SERVICE TITLE ----------------

        root.addView(
            text(
                "What do you need help with?",
                21f,
                dark,
                true
            ),
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(45)
            )
        )

        // ---------------- SERVICE GRID ----------------

        val grid = GridLayout(this).apply {
            columnCount = 2
            rowCount = 4
        }

        val services = listOf(
            "🔧" to "Plumber",
            "⚡" to "Electrician",
            "🪚" to "Carpenter",
            "❄️" to "AC Technician",
            "💧" to "RO / Water\nPurifier",
            "🧺" to "Washing\nMachine",
            "🧊" to "Refrigerator",
            "•••" to "More\nServices"
        )

        services.forEach { (icon, name) ->

            val card = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                setPadding(dp(8), dp(8), dp(8), dp(8))
                background = roundedBackground(Color.WHITE, 18)
                elevation = dp(2).toFloat()

                setOnClickListener {
                    showService(name.replace("\n", " "))
                }
            }

            val iconView = TextView(this).apply {
                text = icon
                textSize = if (icon == "•••") 27f else 38f
                gravity = Gravity.CENTER
                background = roundedBackground(
                    Color.rgb(240, 245, 255),
                    50
                )
            }

            card.addView(
                iconView,
                LinearLayout.LayoutParams(dp(78), dp(78))
            )

            val nameView = text(
                name,
                14f,
                dark,
                true
            )
            nameView.gravity = Gravity.CENTER

            card.addView(
                nameView,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dp(52)
                )
            )

            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = dp(155)
                columnSpec = GridLayout.spec(
                    GridLayout.UNDEFINED,
                    1f
                )
                setMargins(
                    dp(5),
                    dp(5),
                    dp(5),
                    dp(5)
                )
            }

            grid.addView(card, params)
        }

        root.addView(
            grid,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(650)
            )
        )

        // ---------------- EMERGENCY BUTTON ----------------

        val emergency = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(15), dp(12), dp(15), dp(12))
            background = GradientDrawable(
                GradientDrawable.Orientation.LEFT_RIGHT,
                intArrayOf(
                    Color.rgb(255, 65, 40),
                    Color.rgb(235, 40, 30)
                )
            ).apply {
                cornerRadius = dp(22).toFloat()
            }

            setOnClickListener {
                showEmergency()
            }
        }

        val siren = text("🚨", 48f, Color.WHITE)
        emergency.addView(
            siren,
            LinearLayout.LayoutParams(dp(75), dp(80))
        )

        val emergencyText = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        emergencyText.addView(
            text(
                "NEED HELP NOW?",
                20f,
                Color.WHITE,
                true
            )
        )

        emergencyText.addView(
            text(
                "Technicians near you will\nrespond immediately",
                14f,
                Color.WHITE
            )
        )

        emergency.addView(
            emergencyText,
            LinearLayout.LayoutParams(0, dp(85), 1f)
        )

        val go = TextView(this).apply {
            text = "→"
            textSize = 38f
            gravity = Gravity.CENTER
            setTextColor(Color.rgb(210, 45, 35))
            background = roundedBackground(Color.WHITE, 50)
        }

        emergency.addView(
            go,
            LinearLayout.LayoutParams(dp(65), dp(65))
        )

        root.addView(
            emergency,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(105)
            ).apply {
                setMargins(0, dp(12), 0, dp(18))
            }
        )

        // ---------------- BENEFITS ----------------

        val benefits = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            setPadding(dp(5), dp(12), dp(5), dp(12))
            background = roundedBackground(Color.WHITE, 20)
            elevation = dp(2).toFloat()
        }

        val benefitData = listOf(
            "🛡️" to "Verified\nProfessionals",
            "◷" to "Quick\nResponse",
            "₹" to "Affordable\nPricing",
            "⭐" to "Rated &\nReviewed"
        )

        benefitData.forEach { (icon, label) ->

            val b = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
            }

            b.addView(
                text(icon, 27f, blue, true),
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dp(40)
                )
            )

            val labelView = text(
                label,
                12f,
                dark,
                true
            )
            labelView.gravity = Gravity.CENTER

            b.addView(
                labelView,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dp(45)
                )
            )

            benefits.addView(
                b,
                LinearLayout.LayoutParams(0, dp(90), 1f)
            )
        }

        root.addView(
            benefits,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(110)
            ).apply {
                setMargins(0, 0, 0, dp(18))
            }
        )

        // ---------------- BOTTOM NAVIGATION ----------------

        val nav = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
            setPadding(dp(4), dp(8), dp(4), dp(8))
            background = roundedBackground(Color.WHITE, 22)
            elevation = dp(4).toFloat()
        }

        val navItems = listOf(
            "⌂" to "Home",
            "▣" to "Bookings",
            "💬" to "Messages",
            "🏷" to "Offers",
            "♙" to "Profile"
        )

        navItems.forEachIndexed { index, item ->

            val n = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER
                if (index == 0) {
                    background = roundedBackground(
                        Color.rgb(235, 243, 255),
                        18
                    )
                }
            }

            n.addView(
                text(
                    item.first,
                    25f,
                    if (index == 0) blue else dark,
                    true
                ),
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dp(32)
                )
            )

            val label = text(
                item.second,
                11f,
                if (index == 0) blue else dark,
                index == 0
            )
            label.gravity = Gravity.CENTER

            n.addView(
                label,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dp(25)
                )
            )

            nav.addView(
                n,
                LinearLayout.LayoutParams(0, dp(65), 1f)
            )
        }

        root.addView(
            nav,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(75)
            )
        )

        setContentView(scroll)
    }

    private fun showService(service: String) {

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(24), dp(40), dp(24), dp(24))
            setBackgroundColor(Color.rgb(250, 251, 253))
        }

        root.addView(
            text(
                service,
                27f,
                blue,
                true
            )
        )

        root.addView(
            text(
                "Technicians near you",
                20f,
                dark,
                true
            ),
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(55)
            )
        )

        val technicians = listOf(
            "Raj Technician  •  1.2 km  •  ⭐ 4.8",
            "Amit Technician  •  2.1 km  •  ⭐ 4.6",
            "Suresh Technician  •  3.0 km  •  ⭐ 4.7"
        )

        technicians.forEach { technician ->

            val card = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(dp(16), dp(10), dp(16), dp(10))
                background = roundedBackground(Color.WHITE, 18)
                elevation = dp(2).toFloat()
            }

            card.addView(
                text(
                    "👨‍🔧  $technician",
                    15f,
                    dark,
                    true
                )
            )

            val request = Button(this).apply {
                text = "Request Now"
                setOnClickListener {
                    Toast.makeText(
                        this@MainActivity,
                        "Request sent!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            card.addView(request)

            root.addView(
                card,
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    dp(120)
                ).apply {
                    setMargins(0, dp(8), 0, dp(8))
                }
            )
        }

        val back = Button(this).apply {
            text = "← Back to Home"
            setOnClickListener { showHome() }
        }

        root.addView(back)

        setContentView(root)
    }

    private fun showEmergency() {

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(dp(30), dp(40), dp(30), dp(30))
            setBackgroundColor(Color.rgb(250, 251, 253))
        }

        root.addView(
            text("🚨", 70f, orange, true),
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(100)
            )
        )

        root.addView(
            text(
                "Help is on the way!",
                28f,
                blue,
                true
            )
        )

        root.addView(
            text(
                "We're finding available technicians near your location.",
                17f,
                dark
            ),
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                dp(80)
            )
        )

        val back = Button(this).apply {
            text = "Back to Home"
            setOnClickListener { showHome() }
        }

        root.addView(back)

        setContentView(root)
    }
}

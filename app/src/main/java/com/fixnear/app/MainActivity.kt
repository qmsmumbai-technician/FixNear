package com.fixnear.app

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    private val services = arrayOf(
        "🔧  Plumber",
        "⚡  Electrician",
        "🪚  Carpenter",
        "❄️  AC / Refrigerator",
        "🧹  Cleaning Service",
        "🎨  Painter",
        "🔑  Locksmith"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun showHome() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 28, 24, 20)
            setBackgroundColor(Color.rgb(247, 249, 252))
        }

        val scroll = ScrollView(this)
        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        content.addView(TextView(this).apply {
            text = "FixNear"
            textSize = 32f
            setTextColor(Color.rgb(20, 70, 140))
            gravity = Gravity.CENTER
            setTypeface(null, Typeface.BOLD)
        })

        content.addView(TextView(this).apply {
            text = "Find a trusted technician near you"
            textSize = 17f
            setTextColor(Color.DKGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 8, 0, 24)
        })

        content.addView(TextView(this).apply {
            text = "📍  Your location\nTap a service to find technicians nearby"
            textSize = 16f
            setTextColor(Color.rgb(35, 55, 75))
            setPadding(20, 18, 20, 18)
            background = roundedBackground(Color.WHITE, 18)
            elevation = 5f
        }, marginParams(0, 0, 0, 20))

        content.addView(TextView(this).apply {
            text = "Choose a service"
            textSize = 21f
            setTextColor(Color.rgb(30, 30, 30))
            setTypeface(null, Typeface.BOLD)
            setPadding(4, 0, 0, 12)
        })

        val grid = GridLayout(this).apply { columnCount = 2 }

        services.forEach { service ->
            val card = TextView(this).apply {
                text = service
                textSize = 17f
                setTextColor(Color.rgb(25, 45, 70))
                gravity = Gravity.CENTER
                setPadding(10, 24, 10, 24)
                background = roundedBackground(Color.WHITE, 20)
                elevation = 6f
                setOnClickListener { showNearby(service) }
            }
            val params = GridLayout.LayoutParams().apply {
                width = 0
                height = GridLayout.LayoutParams.WRAP_CONTENT
                columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f)
                setMargins(6, 6, 6, 6)
            }
            grid.addView(card, params)
        }

        content.addView(grid)

        content.addView(Button(this).apply {
            text = "👨‍🔧  Technician Mode"
            textSize = 16f
            setOnClickListener { showTechnician() }
        }, marginParams(0, 22, 0, 0))

        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        setContentView(root)
    }

    private fun showNearby(service: String) {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 28, 24, 20)
            setBackgroundColor(Color.rgb(247, 249, 252))
        }

        root.addView(TextView(this).apply {
            text = "$service technicians nearby"
            textSize = 24f
            setTextColor(Color.rgb(20, 70, 140))
            setTypeface(null, Typeface.BOLD)
        })

        listOf(
            "Raj Technician  •  1.2 km  •  ★ 4.8",
            "Amit Technician •  2.1 km  •  ★ 4.6",
            "Suresh Technician •  3.0 km  •  ★ 4.5"
        ).forEach { technician ->
            root.addView(Button(this).apply {
                text = technician
                textSize = 15f
                setOnClickListener {
                    Toast.makeText(this@MainActivity, "Request sent!", Toast.LENGTH_LONG).show()
                }
            }, marginParams(0, 10, 0, 0))
        }

        root.addView(Button(this).apply {
            text = "← Back"
            setOnClickListener { showHome() }
        }, marginParams(0, 20, 0, 0))

        setContentView(root)
    }

    private fun showTechnician() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 28, 24, 20)
            setBackgroundColor(Color.rgb(247, 249, 252))
        }

        root.addView(TextView(this).apply {
            text = "Technician Dashboard"
            textSize = 26f
            setTextColor(Color.rgb(20, 70, 140))
            setTypeface(null, Typeface.BOLD)
        })

        root.addView(Switch(this).apply {
            text = "Online / Available"
            textSize = 17f
            isChecked = true
            setPadding(0, 20, 0, 20)
        })

        root.addView(Button(this).apply {
            text = "📋  Demo Incoming Request"
            setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "New plumber request received",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        root.addView(Button(this).apply {
            text = "← Back"
            setOnClickListener { showHome() }
        }, marginParams(0, 20, 0, 0))

        setContentView(root)
    }

    private fun roundedBackground(color: Int, radius: Int) =
        GradientDrawable().apply {
            setColor(color)
            cornerRadius = radius.toFloat()
            setStroke(1, Color.rgb(225, 230, 238))
        }

    private fun marginParams(l: Int, t: Int, r: Int, b: Int) =
        LinearLayout.LayoutParams(-1, -2).apply {
            setMargins(l, t, r, b)
        }
}
    

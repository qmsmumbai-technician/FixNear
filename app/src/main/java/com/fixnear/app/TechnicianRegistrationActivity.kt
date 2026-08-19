package com.fixnear.app

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.widget.*

class TechnicianRegistrationActivity : Activity() {

    private val blue = Color.rgb(20, 79, 180)
    private val dark = Color.rgb(25, 32, 45)

    private fun dp(v: Int): Int =
        (v * resources.displayMetrics.density).toInt()

    private fun bg(color: Int, radius: Int = 18) =
        GradientDrawable().apply {
            setColor(color)
            cornerRadius = dp(radius).toFloat()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(24), dp(36), dp(24), dp(24))
            setBackgroundColor(Color.rgb(250, 251, 253))
        }

        val logo = TextView(this).apply {
            text = "FixNear"
            textSize = 34f
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(blue)
        }

        root.addView(logo, LinearLayout.LayoutParams(-1, dp(55)))

        val title = TextView(this).apply {
            text = "Technician Registration"
            textSize = 25f
            gravity = Gravity.CENTER
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(dark)
        }

        root.addView(title, LinearLayout.LayoutParams(-1, dp(60)))

        val subtitle = TextView(this).apply {
            text = "Register to receive nearby service requests."
            textSize = 15f
            gravity = Gravity.CENTER
            setTextColor(Color.GRAY)
        }

        root.addView(subtitle, LinearLayout.LayoutParams(-1, dp(45)))

        val name = EditText(this).apply {
            hint = "Full Name"
            textSize = 17f
            setSingleLine(true)
            setPadding(dp(16), 0, dp(16), 0)
            background = bg(Color.WHITE)
        }

        root.addView(
            name,
            LinearLayout.LayoutParams(-1, dp(58)).apply {
                setMargins(0, dp(20), 0, dp(14))
            }
        )

        val mobile = EditText(this).apply {
            hint = "Mobile Number"
            textSize = 17f
            inputType = InputType.TYPE_CLASS_PHONE
            setSingleLine(true)
            setPadding(dp(16), 0, dp(16), 0)
            background = bg(Color.WHITE)
        }

        root.addView(
            mobile,
            LinearLayout.LayoutParams(-1, dp(58)).apply {
                setMargins(0, 0, 0, dp(20))
            }
        )

        val availableRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(16), dp(8), dp(12), dp(8))
            background = bg(Color.WHITE)
        }

        val labels = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        labels.addView(TextView(this).apply {
            text = "Available for work"
            textSize = 17f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(dark)
        })

        labels.addView(TextView(this).apply {
            text = "Turn this on when you are ready to receive requests."
            textSize = 12f
            setTextColor(Color.GRAY)
        })

        availableRow.addView(
            labels,
            LinearLayout.LayoutParams(0, dp(60), 1f)
        )

        val available = Switch(this).apply {
            isChecked = true
        }

        availableRow.addView(
            available,
            LinearLayout.LayoutParams(dp(65), dp(60))
        )

        root.addView(
            availableRow,
            LinearLayout.LayoutParams(-1, dp(80)).apply {
                setMargins(0, 0, 0, dp(30))
            }
        )

        val register = Button(this).apply {
            text = "REGISTER & CONTINUE"
            textSize = 16f
            typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.WHITE)
            background = bg(blue, 20)
        }

        register.setOnClickListener {

            val n = name.text.toString().trim()
            val m = mobile.text.toString().trim()

            if (n.isEmpty()) {
                name.error = "Please enter your name"
                name.requestFocus()
                return@setOnClickListener
            }

            if (m.length < 10) {
                mobile.error = "Please enter a valid mobile number"
                mobile.requestFocus()
                return@setOnClickListener
            }

            getSharedPreferences(
                "fixnear_technician",
                MODE_PRIVATE
            ).edit()
                .putString("name", n)
                .putString("mobile", m)
                .putBoolean("available", available.isChecked)
                .putBoolean("registered", true)
                .apply()

            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )

            finish()
        }

        root.addView(
            register,
            LinearLayout.LayoutParams(-1, dp(58))
        )

        root.addView(TextView(this).apply {
            text = "Your details are stored securely for FixNear services."
            textSize = 12f
            gravity = Gravity.CENTER
            setTextColor(Color.GRAY)
        })

        setContentView(root)
    }
}

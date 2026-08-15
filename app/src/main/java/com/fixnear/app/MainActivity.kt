
package com.fixnear.app

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.*
import android.graphics.Color
import android.view.Gravity

class MainActivity : Activity() {

    private val services = arrayOf(
        "Plumber",
        "Electrician",
        "Carpenter"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun showHome() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 48, 32, 32)
        }

        root.addView(TextView(this).apply {
            text = "FixNear"
            textSize = 32f
            setTextColor(Color.rgb(21, 101, 192))
            gravity = Gravity.CENTER
        })

        root.addView(TextView(this).apply {
            text = "Find a trusted technician near you"
            textSize = 18f
            gravity = Gravity.CENTER
        })

        root.addView(TextView(this).apply {
            text = "Choose a service"
            textSize = 20f
            setPadding(0, 40, 0, 16)
        })

        services.forEach { service ->
            root.addView(Button(this).apply {
                text = service
                setOnClickListener {
                    showNearby(service)
                }
            })
        }

        root.addView(Button(this).apply {
            text = "Technician Mode"
            setOnClickListener {
                showTechnician()
            }
        })

        setContentView(root)

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100
            )
        }
    }

    private fun showNearby(service: String) {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 40, 24, 24)
        }

        root.addView(TextView(this).apply {
            text = "$service technicians nearby"
            textSize = 24f
        })

        listOf(
            "Raj Technician • 1.2 km • ★ 4.8",
            "Amit Technician • 2.1 km • ★ 4.6",
            "Suresh Technician • 3.0 km • ★ 4.5"
        ).forEach { technician ->

            root.addView(Button(this).apply {
                text = technician
                setOnClickListener {
                    Toast.makeText(
                        this@MainActivity,
                        "Request sent!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            })
        }

        root.addView(Button(this).apply {
            text = "Back"
            setOnClickListener { showHome() }
        })

        setContentView(root)
    }

    private fun showTechnician() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 40, 24, 24)
        }

        root.addView(TextView(this).apply {
            text = "Technician Dashboard"
            textSize = 26f
        })

        root.addView(Switch(this).apply {
            text = "Online / Available"
            isChecked = true
        })

        root.addView(Button(this).apply {
            text = "Demo Incoming Request"
            setOnClickListener {
                Toast.makeText(
                    this@MainActivity,
                    "New plumber request received",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        root.addView(Button(this).apply {
            text = "Back"
            setOnClickListener { showHome() }
        })

        setContentView(root)
    }
}

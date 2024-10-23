package com.example.simplecounter

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private var counter = 0
    private lateinit var textViewCounter: TextView
    private lateinit var buttonIncrement: Button
    private lateinit var buttonDecrement: Button
    private lateinit var buttonRes: Button
    private lateinit var preferences: SharedPreferences
    private lateinit var bgColor: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        preferences = getPreferences(Context.MODE_PRIVATE)
        counter = preferences.getInt("key", 0)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textViewCounter = findViewById<TextView>(R.id.TextView1)
        buttonIncrement = findViewById<Button>(R.id.button2)
        buttonDecrement = findViewById<Button>(R.id.button)
        buttonRes = findViewById<Button>(R.id.button3)
        bgColor = findViewById<ConstraintLayout>(R.id.main)
        textViewCounter.text = counter.toString()
        bgColor.setBackgroundColor(Color.argb(50, 0, 255, 0))
        buttonIncrement.setOnClickListener {
            counter++
            textViewCounter.text = counter.toString()
            bgColor.setBackgroundColor(Color.argb(50, 0, 255, 0))
        }

        buttonDecrement.setOnClickListener {
            if (counter > 0){
                counter--
                textViewCounter.text = counter.toString()
                bgColor.setBackgroundColor(Color.argb(50, 255, 0, 0))
            }else {
                counter = 0
                textViewCounter.text = counter.toString()
            }
        }

        buttonRes.setOnClickListener {
            counter = 0
            textViewCounter.text = counter.toString()
        }
    }

    override fun onPause() {
        super.onPause()
        preferences.edit().putInt("key", counter).apply()
    }
}
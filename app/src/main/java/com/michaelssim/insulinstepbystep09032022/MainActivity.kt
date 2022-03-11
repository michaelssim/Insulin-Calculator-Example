package com.michaelssim.insulinstepbystep09032022

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var etTarget: EditText
    private lateinit var etCurrent: EditText
    private lateinit var tvCorrection: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTarget = findViewById(R.id.etTarget)
        etCurrent = findViewById(R.id.etCurrent)
        tvCorrection = findViewById(R.id.tvCorrection)

        etTarget.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged from etTarget: $p0")
                calculateTotal()
            }
        })

        etCurrent.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG, "afterTextChanged from etCurrent: $p0")
                calculateTotal()
            }
        })
    }

    private fun calculateTotal() {
        // 0. Only calculate AFTER required fields are filled in (to prevent NumberFormatException)
        if (etCurrent.text.isEmpty() || etTarget.text.isEmpty()) {
            tvCorrection.text = ""
            return
        }
        // 1. Get the values from user input for `Target` and `Current`
        val targetValue = etTarget.text.toString().toInt()
        val currentValue = etCurrent.text.toString().toInt()
        // 2. Calculate value for `Correction`
        val correctionValue = targetValue - currentValue
        // 3. Update UI and use colors to denote positive values vs. negative values
        if (correctionValue > 0) {
            tvCorrection.setTextColor(Color.GREEN)
        } else if (correctionValue < 0) {
            tvCorrection.setTextColor(Color.RED)
        } else {
            tvCorrection.setTextColor(Color.BLACK)
        }
        tvCorrection.text = correctionValue.toString()
    }
}
package com.example.billscalculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class activity_main : AppCompatActivity() {
    private var unitsEditText: EditText? = null
    private var rebateEditText: EditText? = null
    private var calculateButton: Button? = null
    private var btnAbout: Button? = null
    private var resultTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize views
        unitsEditText = findViewById<EditText>(R.id.NumberElectric)
        rebateEditText = findViewById<EditText>(R.id.rebate)
        calculateButton = findViewById<Button>(R.id.calculate)
        resultTextView = findViewById<TextView>(R.id.Result)
        btnAbout = findViewById<Button>(R.id.About)

        // Calculate button click listener
        calculateButton?.setOnClickListener { calculateBill() }


        // About button click listener
        btnAbout?.setOnClickListener { openAboutPage() }
    }


    private fun calculateBill() {
        val unitsString = unitsEditText!!.text.toString()
        val rebateString = rebateEditText!!.text.toString()

        // Validate input
        if (unitsString.isEmpty() || rebateString.isEmpty()) {
            resultTextView!!.text = "Please enter units and rebate percentage."
            return
        }

        val units = unitsString.toDouble()
        val rebate = rebateString.toDouble()

        var bill = 0.0

        if (units <= 200) {
            bill = units * 0.218
        } else if (units <= 300) {
            val unitsFirstBlock = 200.0
            val unitsNextBlock = units - unitsFirstBlock
            bill = (unitsFirstBlock * 0.218) + (unitsNextBlock * 0.334)
        } else if (units <= 600) {
            val unitsFirstBlock = 200.0
            val unitsSecondBlock = 100.0
            val unitsNextBlock = units - unitsFirstBlock - unitsSecondBlock
            bill = (unitsFirstBlock * 0.218) + (unitsSecondBlock * 0.334) + (unitsNextBlock * 0.516)
        } else {
            val unitsFirstBlock = 200.0
            val unitsSecondBlock = 100.0
            val unitsThirdBlock = 300.0
            val unitsNextBlock = units - unitsFirstBlock - unitsSecondBlock - unitsThirdBlock
            bill =
                (unitsFirstBlock * 0.218) + (unitsSecondBlock * 0.334) + (unitsThirdBlock * 0.516) + (unitsNextBlock * 0.546)
        }

        bill *= (1 - rebate / 100)

        resultTextView!!.text = "Electric Bill: RM" + String.format("%.2f", bill)
    }

    private fun clearInput() {
        unitsEditText!!.setText("")
        rebateEditText!!.setText("")
        resultTextView!!.text = ""
    }

    private fun openAboutPage() {
        val aboutIntent = Intent(
            this,
            About::class.java
        )
        startActivity(aboutIntent)
    }
}

package com.example.bloodbank

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class after : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_after)

        val b = findViewById<TextView>(R.id.l)
        val intent= intent
        var output=intent.getStringExtra("title")
        b.text=output.toString()

        val c = findViewById<TextView>(R.id.b)
        var o=intent.getStringExtra("message")

        System.out.println(o)
        c.text=o.toString()
        val call = findViewById<Button>(R.id.call)
        call.setOnClickListener {
            val phoneNumber = o // The phone number you want to call
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:$phoneNumber")

            startActivity(callIntent)
        }

    }
}
package com.example.bloodbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val b = findViewById<TextView>(R.id.textView)
        val intent= intent
        var output=intent.getStringExtra("a")
        b.text=output.toString()
        val q= findViewById<Button>(R.id.ss)

        q.setOnClickListener{
            val z = Intent(this,needblood::class.java)
            startActivity(z)}
    }
}
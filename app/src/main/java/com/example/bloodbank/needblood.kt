package com.example.bloodbank

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


const val TOPIC = "/topics/myTopic"

class needblood : AppCompatActivity() {
    val TAG = "needblood"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_needblood)


        val closeButton = findViewById<Button>(R.id.x)
        closeButton.setOnClickListener {
            finish() // Finish the current activity
        }

        val s =findViewById<Spinner>(R.id.a)
        val blood= arrayOf("A+","A-","B+","B-","AB+","AB-","o+","o-")
        val aradp=
            ArrayAdapter(this@needblood,android.R.layout.simple_spinner_dropdown_item,blood)
        s.adapter=aradp


        val s2=findViewById<Spinner>(R.id.ab)
        val location= arrayOf("Badda","Mohakhali","Narayanganj","Gulshan","Banani")
        val ac=
            ArrayAdapter(this@needblood,android.R.layout.simple_spinner_dropdown_item,location)
        s2.adapter=ac

        val a=findViewById<Button>(R.id.idBtnRegister)
        val b=findViewById<EditText>(R.id.idEdtphone)
        val c=findViewById<Spinner>(R.id.a)
        val d=findViewById<Spinner>(R.id.ab)


        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC)
        a.setOnClickListener {
            val t = c.getSelectedItem().toString()
            val m = d.getSelectedItem().toString()
            val message = b.text.toString()
            val first="Location: "
            val middle="\nBlood: "
            val title=first+m+middle+t
            System.out.println(message)

            if(title.isNotEmpty() && m.isNotEmpty() && message.isNotEmpty()) {
                PushNotification(
                    NotificationData(title,message),
                    TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }



    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                Log.d(TAG, "Response: ${Gson().toJson(response)}")
            } else {
                Log.e(TAG, response.errorBody().toString())
            }
        } catch(e: Exception) {
            Log.e(TAG, e.toString())
        }
    }
}

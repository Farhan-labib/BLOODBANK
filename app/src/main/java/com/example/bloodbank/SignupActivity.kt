package com.example.bloodbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.bloodbank.databinding.ActivitySignupBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignupActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var idBtnRegister:Button
    private lateinit var dbRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val s=findViewById<Spinner>(R.id.a)
        val blood= arrayOf("A+","A-","B+","B-","AB+","AB-","o+","o-")
        val aradp=
            ArrayAdapter(this@SignupActivity,android.R.layout.simple_spinner_dropdown_item,blood)
        s.adapter=aradp


        val s2=findViewById<Spinner>(R.id.ab)
        val location= arrayOf("Badda","Mohakhali","Narayanganj","Gulshan","Banani")
        val ac=
            ArrayAdapter(this@SignupActivity,android.R.layout.simple_spinner_dropdown_item,location)
        s2.adapter=ac



        firebaseAuth=FirebaseAuth.getInstance()
        binding.idBtnRegister.setOnClickListener {
            val email =binding.idEdtUserName.text.toString()
            val password=binding.idEdtPassword.text.toString()
            val phone=binding.idEdtphone.text.toString()
            val blood=binding.a.getSelectedItem().toString()
            val location=binding.ab.getSelectedItem().toString()
            if(email.isNotEmpty() and password.isNotEmpty() and phone.isNotEmpty() and blood.isNotEmpty() and location.isNotEmpty()){
                firebaseAuth.createUserWithEmailAndPassword(email,password)

                    .addOnCompleteListener(this){task ->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Signup Successfull",Toast.LENGTH_SHORT).show()


                            dbRef=FirebaseDatabase.getInstance().getReference("users")
                            val uid = firebaseAuth.currentUser?.uid ?: ""
                            val u=usermodel(uid,email,phone,blood,location)
                            dbRef.child(uid).setValue(u)




                            val intent = Intent(this,LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Signup Unsuccessfull",Toast.LENGTH_SHORT).show()
                        }

                    }

            }else{ Toast.makeText(this,"All Fields Required",Toast.LENGTH_SHORT).show()

            }


        }
    }
}
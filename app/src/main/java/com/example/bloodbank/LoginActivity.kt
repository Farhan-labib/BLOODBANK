package com.example.bloodbank

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import com.example.bloodbank.databinding.ActivityLoginBinding
import com.example.bloodbank.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener {
            val email=binding.username.text.toString()
            val password=binding.password.text.toString()
            if(email.isNotEmpty() and password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){task->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Login Successfull", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this,MainActivity::class.java)
                            intent.putExtra("a",email)

                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this,"Login Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this,"All Fields Required",Toast.LENGTH_SHORT).show()

            }
        }

        val q= findViewById<Button>(R.id.reg)

        q.setOnClickListener{
            val z = Intent(this,SignupActivity::class.java)
            startActivity(z)


        }
    }}
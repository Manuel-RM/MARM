package com.example.marm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class Login : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val myAuth= FirebaseAuth.getInstance()
        val databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://proyecto-7c5a0-default-rtdb.firebaseio.com/")
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registerButton = findViewById<TextView>(R.id.registerButton)

        loginButton.setOnClickListener {
            val email1 = email.text.toString()
            val password1 = password.text.toString()

            if (email1.isNotEmpty() && password1.isNotEmpty()) {
                myAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val iniciar = Intent(this, HomeActivity::class.java)
                        startActivity(iniciar)
                        finish()
                    } else {
                        Toast.makeText(this, "No se pudo iniciar la sesion", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

            } else {
                Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            val iniciar = Intent(this, Register::class.java)
            startActivity(iniciar)
        }
    }

    //override fun onStart() {
      //  super.onStart()
        //val myAuth = FirebaseAuth.getInstance()
        //if (myAuth.currentUser != null){
          //  val iniciar = Intent(this, HomeActivity::class.java)
            //startActivity(iniciar)
            //finish()
        //}
    //}
}




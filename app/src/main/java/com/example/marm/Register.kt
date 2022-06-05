package com.example.marm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlin.collections.HashMap

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://proyecto-7c5a0-default-rtdb.firebaseio.com/")

        val name = findViewById<EditText>(R.id.name)
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val confirm_password = findViewById<EditText>(R.id.confirm_password)
        val register2Button = findViewById<Button>(R.id.register2Button)
        val login2Button = findViewById<TextView>(R.id.login2Button)

        val myAuth= FirebaseAuth.getInstance()

        register2Button.setOnClickListener {
            val name1 = name.text.toString()
            val username1 = username.text.toString()
            val email1 = email.text.toString()
            val password1 = password.text.toString()
            val confirm_password1 = confirm_password.text.toString()

            if (name1.isNotEmpty() && username1.isNotEmpty() && email1.isNotEmpty() && password1.isNotEmpty()) {
                if (password1.length >= 6) {
                    myAuth.createUserWithEmailAndPassword(email1, password1)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {

                                val id = myAuth.currentUser!!.uid
                                val map = HashMap<String, String>()
                                map.put("name", name1)
                                map.put("username", username1)
                                map.put("email", email1)
                                map.put("password",password1)
                                databaseReference.child("users").child(id).setValue(map)
                                    .addOnCompleteListener { task2 ->
                                        if (task2.isSuccessful) {
                                            val iniciar =
                                                Intent(this, HomeActivity::class.java)
                                            startActivity(iniciar)
                                            finish()
                                        } else {
                                            Toast.makeText(
                                                this,
                                                "No se pudieron crear los datos correctamente",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            } else {
                                Toast.makeText(
                                    this,
                                    "El registro no fue exitoso",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(
                        this,
                        "El password debe tener al menos 6 caracteristicas",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        login2Button.setOnClickListener{
            val iniciar = Intent(this, Login::class.java)
            startActivity(iniciar)
            finish()
        }
    }
}



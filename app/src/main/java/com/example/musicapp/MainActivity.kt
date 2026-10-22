package com.example.musicapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var etCorreo : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnIniciar : Button

    private lateinit var dbHelper : DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        val tvCrearCuenta = findViewById<TextView>(R.id.tvCrearCuenta)
        etCorreo = findViewById(R.id.etCorreo)
        etPassword = findViewById(R.id.etPassword)
        btnIniciar = findViewById(R.id.btnInciar)
        dbHelper = DatabaseHelper(this)

        //crear evento para iniciar sesion
        btnIniciar.setOnClickListener {
            iniciarSesion()
        }



        //crear evento para ir a otra pantalla
        tvCrearCuenta.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    //metodo para iniciar sesion
    fun iniciarSesion() {
        val correo = etCorreo.text.toString().trim()
        val password = etPassword.text.toString().trim()

        if (correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado = dbHelper.iniciarSesion(correo, password)

        if (resultado) {
            Toast.makeText(this, "Bienvenido :D", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            finish()
        } else {
            Toast.makeText(this, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
        }
    }
}
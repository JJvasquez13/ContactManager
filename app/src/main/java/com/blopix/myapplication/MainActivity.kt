package com.blopix.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnPantallaContact: Button = findViewById<Button>(R.id.main_AddContact)
        btnPantallaContact.setOnClickListener(View.OnClickListener {view ->
            val intentPantallaContact = Intent(this, ContactActivity::class.java)
            startActivity(intentPantallaContact)

            Toast.makeText(this, getString(R.string.MensajeContacto).toString(), Toast.LENGTH_LONG).show()
        })

        val btnPantallaContactList: Button = findViewById<Button>(R.id.main_viewContactList)
        btnPantallaContactList.setOnClickListener(View.OnClickListener {view ->
            val intentPantallaContactList = Intent(this, ContactListActivity::class.java)
            startActivity(intentPantallaContactList)

            Toast.makeText(this, getString(R.string.MensajeListaContactos).toString(), Toast.LENGTH_LONG).show()
        })
    }
}
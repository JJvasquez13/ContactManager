package com.blopix.myapplication

import Entities.Contact
import Model.ContactModel
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactActivity : AppCompatActivity() {

    private lateinit var txtId: EditText
    private lateinit var txtName: EditText
    private lateinit var txtLastName: EditText
    private lateinit var txtPhone: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtAddress: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtId = findViewById<EditText>(R.id.txtContact_id)
        txtName = findViewById<EditText>(R.id.txtContact_name)
        txtLastName = findViewById<EditText>(R.id.txtContact_lastName)
        txtPhone = findViewById<EditText>(R.id.txtContact_phone)
        txtEmail = findViewById<EditText>(R.id.txtContact_email)
        txtAddress = findViewById<EditText>(R.id.txtContact_address)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.crud_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_save -> {
                saveContact()
                return true
            }
            R.id.menu_delete -> {
                deleteContact()
                return true
            }
            R.id.menu_cancel -> {
                cleanContact()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun saveContact() {
        try {
            val contact = Contact()
            contact.id = txtId.text.toString()
            contact.name = txtName.text.toString()
            contact.lastName = txtLastName.text.toString()
            contact.phone= txtPhone.text.toString()?.toInt()!!
            contact.email = txtEmail.text.toString()
            contact.address = txtAddress.text.toString()
            if (dataValidation(contact)) {
                ContactModel.addContact(contact)
                cleanContact()
                Toast.makeText(this, R.string.msgSave, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.msgMissingData, Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun dataValidation(contact: Contact): Boolean {
        return contact.id.isNotEmpty() && contact.name.isNotEmpty() &&
                contact.lastName.isNotEmpty() && contact.email.isNotEmpty() &&
                contact.address.isNotEmpty() &&
                (contact.phone != null && contact.phone > 0)
    }

    private fun deleteContact() {

    }

    private fun cleanContact() {
        txtId.setText("")
        txtName.setText("")
        txtLastName.setText("")
        txtPhone.setText("")
        txtEmail.setText("")
        txtAddress.setText("")
    }
}


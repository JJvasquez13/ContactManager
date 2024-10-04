package Model

import Entities.Contact
import android.content.res.Resources
import com.blopix.myapplication.R

class ContactModel {
    companion object {
        private var contactList = mutableListOf<Contact>()

        fun addContact (contact: Contact) {
            contactList.add(contact)
        }

        fun getContacts() = contactList.toList()

        fun getContact(id: String): Contact {
            try {
                val result = contactList.filter { (it.id == id) }
                if (result.any())
                    throw Exception(Resources.getSystem().getString(R.string.msgContactNotFound))
            } catch (e: Exception) {
                throw e
            }
        }

    }
}
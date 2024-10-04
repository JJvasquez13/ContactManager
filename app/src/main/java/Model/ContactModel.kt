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

                return result[0]
            } catch (e: Exception) {
                    throw e
                }
            }


        fun getContactNames(): List<String> {
            val names = mutableListOf<String>()
            contactList.forEach { i -> names.add(i.fullName)}
            return names.toList()
        }

        fun remContact(id: String) {
            try {
                contactList.remove(getContact(id))
            } catch (e: Exception) {
                throw e
            }
        }
    }
}

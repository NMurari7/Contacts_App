package com.example.contacts_roomdb

import android.util.Log
import androidx.lifecycle.LiveData

class RoomRepository(private val contactsDao: ContactsDao) {

    val allContacts : LiveData<List<Contact>> = contactsDao.getAllContacts()

    suspend fun editContact(oldName: String, oldNumber: Long, newName: String, newNumber: Long) {
        Log.d("abcd", "Insert function called in repository")
        contactsDao.editContact(oldName, oldNumber, newName, newNumber)
    }

    suspend fun insertContact(contact: Contact) {
        Log.d("abcd", "Insert function called in repository")
        contactsDao.InsertContact(contact)
    }

    suspend fun deleteContact(contact: Contact) {
        Log.d("abcd", "Delete function called in repository")
        contactsDao.DeleteContact(contact)
    }

}

package com.example.contacts_roomdb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.contacts_roomdb.Contact

class RoomRepository(private val contactsDao: ContactsDao) {

    val allContacts : LiveData<List<Contact>> = contactsDao.getAllContacts()

    suspend fun insertContact(contact: Contact) {
        Log.d("abcd", "Insert function called in repository")
        contactsDao.UpsertContact(contact)
    }

    suspend fun deleteContact(contact: Contact) {
        Log.d("abcd", "Delete function called in repository")
        contactsDao.DeleteContact(contact)
    }

}

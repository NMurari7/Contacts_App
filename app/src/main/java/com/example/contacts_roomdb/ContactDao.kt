package com.example.contacts_roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.contacts_roomdb.Contact

@Dao
interface ContactsDao{

    @Query("SELECT * FROM Contact")
    fun getAllContacts() : LiveData<List<Contact>>

    @Upsert
    suspend fun UpsertContact(contact : Contact)

    @Delete
    suspend fun DeleteContact(contact: Contact)

}
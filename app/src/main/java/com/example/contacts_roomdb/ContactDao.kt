package com.example.contacts_roomdb

import android.icu.text.UnicodeSet.CASE
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.example.contacts_roomdb.Contact

@Dao
interface ContactsDao{

    @Query("SELECT * FROM Contact")
    fun getAllContacts() : LiveData<List<Contact>>


//    AND
//    (:newNumber NOT IN (SELECT contactNumber FROM Contact))
    @Query(""" 
    UPDATE Contact
    SET 
    contactName = CASE WHEN contactName = :oldName THEN :newName ELSE contactName END,
    contactNumber = CASE WHEN contactNumber = :oldNumber THEN :newNumber ELSE contactNumber END
    WHERE (contactName = :oldName AND contactNumber = :oldNumber)         
""")
    suspend fun editContact(oldName: String, oldNumber: Long, newName: String, newNumber: Long)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun UpsertContact(contact : Contact)

    @Delete
    suspend fun DeleteContact(contact: Contact)

}
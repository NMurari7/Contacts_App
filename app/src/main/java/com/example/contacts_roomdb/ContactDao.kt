package com.example.contacts_roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactsDao{

    @Query("SELECT * FROM Contact")
    fun getAllContacts() : LiveData<List<Contact>>


//    AND
//    (:newNumber NOT IN (SELECT contactNumber FROM Contact))
    @Query(
        """ 
    UPDATE Contact
    SET 
    cName = CASE WHEN cName = :oldName THEN :newName ELSE cName END,
    contactNum = CASE WHEN contactNum = :oldNumber THEN :newNumber ELSE contactNum END
    WHERE (cName = :oldName AND contactNum = :oldNumber) AND (:newNumber NOT IN (SELECT contactNum FROM Contact))        
"""
    )
//    For migration
//    suspend fun editContact(oldName: String, oldNumber: Long, newName: String, newNumber: Long)
    suspend fun editContact(oldName: String, oldNumber: Long, newName: String, newNumber: Long)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun InsertContact(contact : Contact)

    @Delete
    suspend fun DeleteContact(contact: Contact)

}
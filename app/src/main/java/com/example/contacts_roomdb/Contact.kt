package com.example.contacts_roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
data class Contact(
    @PrimaryKey()
    val contactName: String,
    val contactNumber : Long
)
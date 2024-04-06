package com.example.contacts_roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Contact")
data class Contact(

    val cName: String,

    @PrimaryKey()
    val contactNum : Long,


    )


package com.example.contacts_roomdb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.contacts_roomdb.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(private val repository: RoomRepository) : ViewModel() {

    private val _allContacts: LiveData<List<Contact>> = repository.allContacts

    init {
        Log.d("abcd", "viewModel Created")
    }

    val allContacts: LiveData<List<Contact>>
        get() = _allContacts


    fun insertContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("abcd", "Insert function called in viewModel")
            repository.insertContact(contact)
        }
    }

    fun deleteContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("abcd", "Delete function called in viewModel")
            repository.deleteContact(contact)
        }
    }
}

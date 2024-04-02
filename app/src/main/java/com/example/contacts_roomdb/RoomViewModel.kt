package com.example.contacts_roomdb

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RoomViewModelFactory(private val repository: RoomRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}

class RoomViewModel(private val repository: RoomRepository) : ViewModel() {

    private val _allContacts: LiveData<List<Contact>> = repository.allContacts

    init {
        Log.d("abcd", "viewModel Created")
    }

    val allContacts: LiveData<List<Contact>>
        get() = _allContacts


    fun editContact(oldName: String, oldNumber: Long, newName: String, newNumber: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("abcd", "Insert function called in viewModel")
            repository.editContact(oldName, oldNumber, newName, newNumber)
        }
    }

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

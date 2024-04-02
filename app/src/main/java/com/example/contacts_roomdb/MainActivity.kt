package com.example.contacts_roomdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.contacts_roomdb.ui.theme.Contacts_RoomDBTheme





class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Contacts_RoomDBTheme {
                
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userDao = AppDatabase.getDatabase(this).roomDao()
                    val roomRepository : RoomRepository = RoomRepository(userDao)
                    val roomViewModel: RoomViewModel by viewModels {
                        RoomViewModelFactory(roomRepository)
                    }
//                    val rooVM : RoomViewModel = viewModel()
                    HomeScreen(roomViewModel)
                }
            }
        }
    }
}


package com.example.contacts_roomdb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import com.example.contacts_roomdb.ui.theme.Contacts_RoomDBTheme
import com.example.contacts_roomdb.RoomViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Contacts_RoomDBTheme {
                
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val roomViewModel: RoomViewModel = viewModel()
                    HomeScreen(roomViewModel)
                }
            }
        }
    }
}


//
//@Composable
//fun RoomScreen() {
////    val viewModel: RoomViewModel = viewModel()
//    val viewModel : RoomViewModel = viewModel()
//    val contactsList by viewModel.allContacts.collectAsState(emptyList())
//    var showDialog by remember { mutableStateOf(false) }
//    var contactName by remember { mutableStateOf("") }
//    var contactNumber by remember { mutableStateOf(7892020166) }
//    var contactEmail by remember { mutableStateOf("") }
//
//    Column {
//        FloatingActionButton(
//            onClick = { showDialog = true },
//            modifier = Modifier
//                .align(Alignment.End)
//                .padding(16.dp)
//        ) {
//            Icon(Icons.Default.Add, contentDescription = "Add Room")
//        }
//
//        LazyColumn {
//            items(allRooms) { room ->
//                RoomCard(room = room) { roomId ->
//                    viewModel.deleteRoom(roomId)
//                }
//            }
//        }
//    }
//
//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = {
//                showDialog = false
//                roomName = TextFieldValue()
//                roomCapacity = TextFieldValue()
//            },
//            title = { Text("Add New Room") },
//            text = {
//                Column {
//                    TextField(
//                        value = roomName,
//                        onValueChange = { roomName = it },
//                        label = { Text("Room Name") }
//                    )
//                    TextField(
//                        value = roomCapacity,
//                        onValueChange = { roomCapacity = it },
//                        label = { Text("Room Capacity") }
//                    )
//                }
//            },
//            confirmButton = {
//                Button(
//                    onClick = {
//                        viewModel.insertRoom(
//                            androidx.room.Room(
//                                roomName = roomName.text,
//                                roomCapacity = roomCapacity.text.toInt()
//                            )
//                        )
//                        showDialog = false
//                        roomName = TextFieldValue()
//                        roomCapacity = TextFieldValue()
//                    }) {
//                    Text("Save")
//                }
//            },
//            dismissButton = {
//                Button(
//                    onClick = {
//                        showDialog = false
//                        roomName = TextFieldValue()
//                        roomCapacity = TextFieldValue()
//                    }) {
//                    Text("Cancel")
//                }
//            }
//        )
//    }
//}
//
//@Composable
//fun RoomCard(room: Room, onDeleteClicked: (Long) -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//    ) {
//        Row(
//            modifier = Modifier.padding(8.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(
//                text = "Room: ${room.roomName}, Capacity: ${room.roomCapacity}",
//                modifier = Modifier.weight(1f)
//            )
//            IconButton(onClick = { onDeleteClicked(room.id) }) {
//                Icon(Icons.Default.Delete, contentDescription = "Delete")
//            }
//        }
//    }
//}
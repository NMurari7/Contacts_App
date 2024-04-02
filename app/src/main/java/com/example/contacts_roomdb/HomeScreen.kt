package com.example.contacts_roomdb

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun HomeScreen(roomViewModel: RoomViewModel) {
    val contactsList by roomViewModel.allContacts.observeAsState(emptyList())
    var openDialog = remember { mutableStateOf(false) }
    Log.d("abcd", "Screen Created")
    Scaffold (
        floatingActionButton = {FAButton(openDialog)}
    ){
        padding ->
        LazyList(contactsList, Modifier.padding(padding), roomViewModel, openDialog )
        if (openDialog.value == true){
            DialogBoxToAdd(openDialog = openDialog, roomViewModel)
        }
    }
}

@Composable
fun LazyList(contactList : List<Contact>, modifier: Modifier = Modifier, viewModel: RoomViewModel, openDialog: MutableState<Boolean>){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF181516))
        .padding(top = 8.dp)){
        items(contactList){contact ->
            SingleCard(contact, viewModel, openDialog)
//                , viewModel)
            Spacer(modifier = Modifier.height(2.dp))
        }
    }

}

@Composable
fun FAButton(openDialog: MutableState<Boolean>){
    FloatingActionButton(
        onClick = { openDialog.value = true },
    ) {
        Icon(Icons.Filled.Add, "Floating action button.")
    }
}





@Composable
fun DialogBoxToAdd(openDialog: MutableState<Boolean>, viewModel: RoomViewModel){

    Dialog(onDismissRequest = { openDialog.value = !openDialog.value }) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
            shape = RoundedCornerShape(16.dp)) {
            Column(modifier = Modifier) {

                var nameState by remember {
                    mutableStateOf(false
                    )
                }
                var Name by remember { mutableStateOf("") }

                OutlinedTextField(value = Name,
                    onValueChange = {Name = it
                    },
                    label = { Text(text = "Name")},
                    modifier = Modifier.padding(16.dp)
                )
                var numberState by remember {
                    mutableStateOf(false)
                }
                var Number by remember { mutableStateOf("") }
                OutlinedTextField(value = Number, onValueChange = {
                    Number = it.toLongOrNull()?.toString() ?: Number
                         },
                    label = { Text(text = "Number")},
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )

//

                Row(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Button(onClick = { openDialog.value = !openDialog.value}, modifier = Modifier
                        .weight(1f)
//                        .padding(start = 14.dp, end = 14.dp)
                    )
                    {
                        Text(text = "Cancel")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(onClick = {
                        openDialog.value = !openDialog.value
                        viewModel.insertContact(Contact(Name, Number.toLong()))
                        Name = ""
                        Number = ""
//
                    }, enabled = (Number.length == 10 && Name.length>1),
                        modifier = Modifier
                        .weight(1f)
//                        .padding(start = 14.dp, end = 14.dp)
                    ) {
                        Text(text = "Add")
                    }
                }

            }
        }
    }
}



@Composable
fun DialogBoxToEdit(openDialogEdit: MutableState<Boolean>, viewModel: RoomViewModel, contact: Contact){

    Dialog(onDismissRequest = { openDialogEdit.value = !openDialogEdit.value }) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
            shape = RoundedCornerShape(16.dp)) {
            Column(modifier = Modifier) {

                var newName by remember { mutableStateOf(contact.contactName) }

                OutlinedTextField(value = newName,
                    onValueChange = {newName = it},
                    label = { Text(text = "Name")},
                    modifier = Modifier.padding(16.dp)
                )

                var newNumber by remember { mutableStateOf(contact.contactNumber.toString()) }
                OutlinedTextField(value = newNumber, onValueChange = {
                    newNumber = it
                                                                     //                    if (it.isEmpty()) {
//                        "0"
//
//                    } else {
//                        it.toLongOrNull()?.toString() ?: newNumber }
                },
                    label = { Text(text = "Number")},
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )

//

                Row(
                    modifier = Modifier.padding(24.dp)
                ) {
                    Button(onClick = { openDialogEdit.value = !openDialogEdit.value}, modifier = Modifier
                        .weight(1f)
//                        .padding(start = 14.dp, end = 14.dp)
                    )
                    {
                        Text(text = "Cancel")
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(onClick = {
                        openDialogEdit.value = !openDialogEdit.value
//                        viewModel.insertContact(Contact(Name, Number.toLong()))
                        viewModel.editContact(contact.contactName, contact.contactNumber, newName, newNumber.toLong() )

//
                    }, modifier = Modifier
                        .weight(1f)
//                        .padding(start = 14.dp, end = 14.dp)
                    ) {
                        Text(text = "Add")
                    }
                }

            }
        }
    }
}





@Composable
fun SingleCard(contact: Contact
               ,viewModel: RoomViewModel,
               openDialog: MutableState<Boolean>
               )
{

    var dialogOpen = remember {
        mutableStateOf(false)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFF437A4E), shape = RoundedCornerShape(6.dp))
        .padding(14.dp),
//        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {

        Column(
            modifier = Modifier.width(250.dp)
        ) {
            Text(
                text = contact.contactName,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif,
                fontSize = 24.sp,
                maxLines = 1,
                color = Color(0XFFFFE7E7)

            )

            Text(
                text = "${contact.contactNumber}",
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif,
                fontSize = 16.sp,
                maxLines = 10,
                color = Color(0xFFDBCAD0),
                modifier = Modifier.width(240.dp)
            )
        }



        Row {
            IconButton(onClick = {
                dialogOpen.value = true

            }) {
                Icon(Icons.Filled.Edit, contentDescription = "Edit icon", tint = Color.Black )
            }


            IconButton(onClick = {
                viewModel.deleteContact(contact)
            }) {
                Icon(Icons.Filled.Delete, contentDescription = "Delete icon", tint = Color.Black)
            }
        }

    }
    if (dialogOpen.value){
        DialogBoxToEdit(openDialogEdit = dialogOpen, viewModel = viewModel , contact = contact )
    }

}
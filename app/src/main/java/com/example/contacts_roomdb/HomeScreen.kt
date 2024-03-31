package com.example.contacts_roomdb

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.contacts_roomdb.ui.theme.RoomViewModel

@Composable
fun HomeScreen(roomViewModel: RoomViewModel) {
    val contactsList by roomViewModel.allContacts.observeAsState(emptyList())
    var openDialog = remember { mutableStateOf(false) }
    Log.d("abcd", "Screen Created")
    Scaffold (
        floatingActionButton = {FAButton(openDialog)}
    ){
        padding ->
        LazyList(contactsList, Modifier.padding(padding), roomViewModel )
        if (openDialog.value == true){
            DialogBoxToAdd(openDialog = openDialog, roomViewModel)
        }
    }

}

@Composable
fun LazyList(Notes : List<Contact>, modifier: Modifier = Modifier, viewModel: RoomViewModel){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = Color(0xFF181516))
        .padding(top = 8.dp)){
        items(Notes){contact ->
            SingleCard(contact, viewModel)
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
fun SingleCard(contact: Contact
               ,viewModel: RoomViewModel)
{

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFF944E63), shape = RoundedCornerShape(6.dp))
        .padding(14.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically) {

        Column(
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
                color = Color(0XFFCAA6A6),
                modifier = Modifier.width(280.dp)
            )
        }
        IconButton(onClick = {
//            viewModel.deleteContact(contact)
        }) {
            Icon(Icons.Filled.Delete, contentDescription = "Delete icon", tint = Color.Black)
        }
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

                var Name by remember { mutableStateOf("") }

                OutlinedTextField(value = Name,
                    onValueChange = {Name = it},
                    label = { Text(text = "Name")},
                    modifier = Modifier.padding(16.dp)
                )

                var Number by remember { mutableStateOf("") }
                OutlinedTextField(value = Number, onValueChange = {
                    Number = if (it.isEmpty()) {
                            ""
                    } else {
                            it.toLongOrNull()?.toString() ?: Number }
                         },
                    label = { Text(text = "Number")},
                    modifier = Modifier.padding(16.dp)
                )

//

                Row {
                    Button(onClick = { openDialog.value = !openDialog.value}, modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp, end = 16.dp)) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = {
                        openDialog.value = !openDialog.value
                        viewModel.insertContact(Contact(Name, Number.toLong()))
//
                    }, modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp, end = 16.dp)) {
                        Text(text = "Add")
                    }
                }

            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun SingleCardPreview(){
//    SingleCard(contact = Contact("Murari", 7892020166))

}
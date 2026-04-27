package com.example.clinicnotesapp.screens

import android.widget.Button
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.clinicnotesapp.model.Note
import com.example.clinicnotesapp.model.SyncStatus
import com.example.clinicnotesapp.viewmodel.NoteViewModel

@Composable
fun NoteScreen(viewModel: NoteViewModel) {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("New Note") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    viewModel.addNote(text)
                    text = ""
                }
            ) {
                Text("Add")
            }

        }
        Spacer(modifier = Modifier.width(8.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(viewModel.notes, key = {it.id}) {note ->
                NoteCard(note)
            }
        }
    }
}

@Composable
fun NoteCard(note: Note) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(note.content, modifier = Modifier.fillMaxWidth(0.85f))
            SyncIndicator(note.status)
        }

    }
}

@Composable
fun SyncIndicator(status: SyncStatus) {
    when (status) {
        SyncStatus.PENDING -> Text("Pending")
        SyncStatus.SYNCED -> Text("Synced")
        SyncStatus.SYNCING -> Text("Syncing")
        SyncStatus.FAILED -> Text("Failed")
    }
}
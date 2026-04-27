package com.example.clinicnotesapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.clinicnotesapp.model.Note
import com.example.clinicnotesapp.model.SyncStatus
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.delay

class NoteViewModel: ViewModel() {
    val notes =mutableStateListOf<Note>()
    fun addNote(text: String) {
        if (text.isBlank()) return

        val newNote = Note(content = text)
        notes.add(0, newNote)

        simulateSync(newNote.id)
    }


    private fun simulateSync(id: String) {
        viewModelScope.launch {
            updateStatus(id, SyncStatus.SYNCING)
            delay(2000)
            val isSuccess = (1..10).random() > 3
            if (isSuccess) {
                updateStatus(id, SyncStatus.SYNCED)
            } else {
                updateStatus(id, SyncStatus.FAILED)
            }
        }
    }
    private fun updateStatus(id: String, status: SyncStatus) {
        val index = notes.indexOfFirst { it.id == id }
        if (index != -1) {
            notes[index] = notes[index].copy(status = status)
        }
    }

}
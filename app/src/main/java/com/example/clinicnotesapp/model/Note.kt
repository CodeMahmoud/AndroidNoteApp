package com.example.clinicnotesapp.model

import java.util.UUID

enum class SyncStatus {
    PENDING,
    SYNCED,
    SYNCING,
    FAILED
}

data class Note(
    val id: String = UUID.randomUUID().toString(),
    val content: String,
    val status: SyncStatus = SyncStatus.PENDING
)

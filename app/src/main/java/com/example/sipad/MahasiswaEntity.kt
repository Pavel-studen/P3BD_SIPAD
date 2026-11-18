package com.example.sipad

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mahasiswa")
data class MahasiswaEntity(
    @PrimaryKey
    val nim: String,
    val nama: String,
    val email: String = "",
    val angkatan: String = "",
    val roleUtama: String = "",
    val roleKedua: String = "",
    val skillset: String = "",
    val portfolioUri: String? = null,
    val fotoProfilUri: String? = null,
    val hasSubmitted: Boolean = false,
    val isFinalized: Boolean = false
)

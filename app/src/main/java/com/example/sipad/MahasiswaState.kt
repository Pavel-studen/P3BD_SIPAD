package com.example.sipad

data class MahasiswaState(
    val hasSubmitted: Boolean = false,
    val isFinalized: Boolean = false,

    // Data profil mahasiswa
    val nama: String = "Paul Efraim",
    val nim: String = "565845",

    // Pilihan mahasiswa
    val roleUtama: String = "",
    val roleKedua: String = "",
    val skillset: List<String> = emptyList(),

    // Hasil penetapan dosen
    val nomorKelompok: Int? = null,
    val anggotaKelompok: List<AnggotaKelompok> = emptyList()
)

data class AnggotaKelompok(
    val nama: String,
    val nim: String,
    val role: String
)

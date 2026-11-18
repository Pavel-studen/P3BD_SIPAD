package com.example.sipad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MahasiswaViewModel(
    private val repository: MahasiswaRepository
) : ViewModel() {

    // Mengamati seluruh data mahasiswa
    val allMahasiswa: StateFlow<List<MahasiswaEntity>> =
        repository.getAllMahasiswa().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Mengambil mahasiswa berdasarkan NIM
    fun getMahasiswaByNim(nim: String): Flow<MahasiswaEntity?> {
        return repository.getMahasiswaByNim(nim)
    }

    // Menambahkan mahasiswa
    fun insertMahasiswa(mahasiswa: MahasiswaEntity) {
        viewModelScope.launch {
            repository.insertMahasiswa(mahasiswa)
        }
    }

    // Memperbarui data mahasiswa
    fun updateMahasiswa(mahasiswa: MahasiswaEntity) {
        viewModelScope.launch {
            repository.updateMahasiswa(mahasiswa)
        }
    }

    // Menghapus mahasiswa
    fun deleteMahasiswa(mahasiswa: MahasiswaEntity) {
        viewModelScope.launch {
            repository.deleteMahasiswa(mahasiswa)
        }
    }
}

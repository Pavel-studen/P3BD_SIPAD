package com.example.sipad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MahasiswaViewModelFactory(
    private val repository: MahasiswaRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {
        if (modelClass.isAssignableFrom(MahasiswaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MahasiswaViewModel(repository) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

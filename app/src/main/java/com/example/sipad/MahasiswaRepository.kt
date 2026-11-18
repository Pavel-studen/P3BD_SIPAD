package com.example.sipad

import kotlinx.coroutines.flow.Flow

class MahasiswaRepository(
    private val mahasiswaDao: MahasiswaDao
) {

    // Mengambil seluruh data mahasiswa
    fun getAllMahasiswa(): Flow<List<MahasiswaEntity>> {
        return mahasiswaDao.getAllMahasiswa()
    }

    // Mengambil mahasiswa berdasarkan NIM
    fun getMahasiswaByNim(nim: String): Flow<MahasiswaEntity?> {
        return mahasiswaDao.getMahasiswaByNim(nim)
    }

    // Menambahkan mahasiswa
    suspend fun insertMahasiswa(mahasiswa: MahasiswaEntity) {
        mahasiswaDao.insertMahasiswa(mahasiswa)
    }

    // Memperbarui data mahasiswa
    suspend fun updateMahasiswa(mahasiswa: MahasiswaEntity) {
        mahasiswaDao.updateMahasiswa(mahasiswa)
    }

    // Menghapus mahasiswa
    suspend fun deleteMahasiswa(mahasiswa: MahasiswaEntity) {
        mahasiswaDao.deleteMahasiswa(mahasiswa)
    }
}

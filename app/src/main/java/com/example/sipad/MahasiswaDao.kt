package com.example.sipad

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MahasiswaDao {

    // Menambahkan data mahasiswa
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMahasiswa(mahasiswa: MahasiswaEntity)

    // Mengambil seluruh data mahasiswa
    @Query("SELECT * FROM mahasiswa")
    fun getAllMahasiswa(): Flow<List<MahasiswaEntity>>

    // Mengambil mahasiswa berdasarkan NIM
    @Query("SELECT * FROM mahasiswa WHERE nim = :nim")
    fun getMahasiswaByNim(nim: String): Flow<MahasiswaEntity?>

    // Memperbarui data mahasiswa
    @Update
    suspend fun updateMahasiswa(mahasiswa: MahasiswaEntity)

    // Menghapus data mahasiswa
    @Delete
    suspend fun deleteMahasiswa(mahasiswa: MahasiswaEntity)
}

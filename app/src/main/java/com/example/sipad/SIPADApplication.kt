package com.example.sipad

import android.app.Application

class SIPADApplication : Application() {

    lateinit var database: SIPADDatabase
        private set

    lateinit var mahasiswaRepository: MahasiswaRepository
        private set

    lateinit var mahasiswaViewModelFactory: MahasiswaViewModelFactory
        private set

    override fun onCreate() {
        super.onCreate()

        // Membuat database
        database = SIPADDatabase.getDatabase(this)

        // Mengambil DAO dari database
        val mahasiswaDao = database.mahasiswaDao()

        // Membuat Repository
        mahasiswaRepository = MahasiswaRepository(mahasiswaDao)

        // Membuat ViewModel Factory
        mahasiswaViewModelFactory =
            MahasiswaViewModelFactory(mahasiswaRepository)
    }
}
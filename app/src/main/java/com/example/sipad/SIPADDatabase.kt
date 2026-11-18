package com.example.sipad

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [MahasiswaEntity::class],
    version = 3,
    exportSchema = false
)
abstract class SIPADDatabase : RoomDatabase() {

    abstract fun mahasiswaDao(): MahasiswaDao

    companion object {
        @Volatile
        private var INSTANCE: SIPADDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE mahasiswa ADD COLUMN portfolioUri TEXT"
                )
            }
        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "ALTER TABLE mahasiswa ADD COLUMN email TEXT NOT NULL DEFAULT ''"
                )
                db.execSQL(
                    "ALTER TABLE mahasiswa ADD COLUMN angkatan TEXT NOT NULL DEFAULT ''"
                )
                db.execSQL(
                    "ALTER TABLE mahasiswa ADD COLUMN fotoProfilUri TEXT"
                )
            }
        }

        fun getDatabase(context: Context): SIPADDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SIPADDatabase::class.java,
                    "sipad_database"
                )
                    .addMigrations(
                        MIGRATION_1_2,
                        MIGRATION_2_3
                    )
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}

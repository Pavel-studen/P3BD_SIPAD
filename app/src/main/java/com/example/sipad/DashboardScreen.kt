package com.example.sipad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.first
import androidx.compose.runtime.remember

@Composable
fun DashboardScreen(
    navController: NavController,
    mahasiswaViewModel: MahasiswaViewModel
) {

    // NIM mahasiswa demo untuk sementara
    val nimMahasiswa = "565845"

    // Mengamati data mahasiswa dari database
    val mahasiswaFlow = remember(mahasiswaViewModel, nimMahasiswa) {
        mahasiswaViewModel.getMahasiswaByNim(nimMahasiswa)
    }

    val mahasiswa by mahasiswaFlow.collectAsState(initial = null)

    // Membuat data awal jika belum tersedia di database
    LaunchedEffect(Unit) {
        val dataAwal = mahasiswaViewModel
            .getMahasiswaByNim(nimMahasiswa)
            .first()

        if (dataAwal == null) {
            mahasiswaViewModel.insertMahasiswa(
                MahasiswaEntity(
                    nim = nimMahasiswa,
                    nama = "Paul Efraim"
                )
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {

        // Header Biru
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1976D2))
                .padding(horizontal = 16.dp, vertical = 20.dp)
        ) {
            Text(
                text = "SIPAD",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Text(
                text = "Sistem Informasi PAD",
                color = Color.White,
                fontSize = 12.sp
            )
        }

        Column(modifier = Modifier.padding(16.dp)) {

            // Menampilkan data mahasiswa
            if (mahasiswa == null) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }

            } else {

                Text(
                    text = "Hai! ${mahasiswa!!.nama},",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Text(
                    text = "Hari ini mau ngapain?",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Status pilihan mahasiswa
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFE3F2FD)
                    ),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        when {
                            mahasiswa!!.isFinalized -> {
                                Text(
                                    text = "Hasil Telah Difinalisasi",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1976D2),
                                    fontSize = 18.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(
                                    text = "Penetapan role telah selesai. Detail kelompok akan ditampilkan di halaman Hasil Role.",
                                    color = Color.DarkGray,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }

                            mahasiswa!!.hasSubmitted -> {
                                Text(
                                    text = "Menunggu Finalisasi",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1976D2),
                                    fontSize = 18.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(
                                    text = "Pilihan role dan skillset kamu sudah dikirim. Silakan menunggu hasil penetapan kelompok.",
                                    color = Color.DarkGray,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }

                            else -> {
                                Text(
                                    text = "Belum Memilih Peran",
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1976D2),
                                    fontSize = 18.sp
                                )

                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )

                                Text(
                                    text = "Yuk, lengkapi profil, pilihan role, dan skillset kamu!",
                                    color = Color.DarkGray,
                                    fontSize = 12.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Tombol Pilih Peran
                Button(
                    onClick = {
                        navController.navigate("profil")
                    },
                    enabled = !mahasiswa!!.hasSubmitted,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF1976D2),
                        disabledContainerColor = Color.LightGray
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = if (mahasiswa!!.hasSubmitted) {
                            "Pilihan Sudah Dikirim"
                        } else {
                            "Pilih Peran"
                        },
                        fontSize = 16.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

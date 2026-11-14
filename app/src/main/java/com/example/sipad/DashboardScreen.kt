package com.example.sipad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sipad.ui.theme.SIPADTheme

@Composable
fun DashboardScreen(navController: NavController) {
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
            // Greeting
            Text(
                text = "Hai! Paul Efraim,",
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

            // Card Info Kelompok
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)), // Biru sangat muda
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp).fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Kelompok 5",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2),
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Selamat! Teman kelompok PAD kamu udah ada nih!",
                        color = Color.DarkGray,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // List Anggota
            DashboardMemberItem(role = "Project Manager", name = "Caroline Emmanuelle", nim = "NIM 556977")
            DashboardMemberItem(role = "UI/UX", name = "Michael Harold", nim = "NIM 568209")
            DashboardMemberItem(role = "Frontend", name = "Paul Efraim", nim = "NIM 565845")
            DashboardMemberItem(role = "Backend", name = "Palava Ganesha", nim = "NIM 559909")

            Spacer(modifier = Modifier.height(24.dp))

            // Tombol Pilih Peran
            Button(
                onClick = { navController.navigate("profil") }, // Navigasi ke halaman Profil
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Pilih Peran", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

// Komponen reusable untuk item list di Dashboard
@Composable
fun DashboardMemberItem(role: String, name: String, nim: String) {
    Column(modifier = Modifier.padding(bottom = 12.dp)) {
        Text(
            text = role,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = name,
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    text = nim,
                    color = Color.DarkGray,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Dashboard")
@Composable
fun DashboardPreview() {
    SIPADTheme {
        DashboardScreen(navController = rememberNavController())
    }
}
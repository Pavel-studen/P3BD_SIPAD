package com.example.sipad

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PengaduanScreen(navController: NavController) {

    Scaffold(
        topBar = {
            SIPADTopAppBar(
                title = "Pengaduan"
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "Halaman Pengaduan",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1976D2)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Halaman ini digunakan mahasiswa " +
                        "untuk menyampaikan pengaduan terkait " +
                        "pelaksanaan PAD.",
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Fitur formulir pengaduan akan " +
                        "dikembangkan pada tahap berikutnya.",
                color = Color.Gray
            )
        }
    }
}

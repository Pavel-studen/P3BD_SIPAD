package com.example.sipad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sipad.ui.theme.SIPADTheme

@Composable
fun HasilRoleScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        SIPADTopAppBar(title = "Hasil Role & Tim", onBackClick = { navController.popBackStack() })

        Column(modifier = Modifier.padding(16.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(24.dp))
            Icon(Icons.Default.Star, contentDescription = null, modifier = Modifier.size(64.dp), tint = Color(0xFF93C5FD))
            Text("Selamat!", fontWeight = FontWeight.Bold, fontSize = 28.sp, color = Color(0xFF1E3A8A), modifier = Modifier.padding(vertical = 16.dp))
            Text("Yay! Role dan Tim PAD kamu udah ada! bisa kamu langsung buka kartu nyaa", textAlign = androidx.compose.ui.text.style.TextAlign.Center, color = Color(0xFF475569))

            Spacer(modifier = Modifier.height(24.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                // Placeholder Cards
                Box(modifier = Modifier.size(140.dp, 200.dp).background(Color.LightGray, RoundedCornerShape(8.dp)))
                Box(modifier = Modifier.size(140.dp, 200.dp).background(Color.LightGray, RoundedCornerShape(8.dp)))
            }

            Spacer(modifier = Modifier.height(24.dp))
            Text("Semoga role kamu sesuai ya!", color = Color.Gray)
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigate("detail_kelompok") },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Detail Kelompok 5", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun DetailKelompokScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        SIPADTopAppBar(title = "Detail Kelompok 5", onBackClick = { navController.popBackStack() })

        Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
            TeamMemberCard("Project Manager", "Caroline Emmanuelle", "NIM 556977")
            TeamMemberCard("UI/UX", "Michael Harold", "NIM 568209")
            TeamMemberCard("Frontend", "Paul Efraim", "NIM 565845")
            TeamMemberCard("Backend", "Palava Ganesha", "NIM 559909")

            Divider(modifier = Modifier.padding(vertical = 16.dp), color = Color(0xFF1976D2), thickness = 2.dp)

            Text("Project", fontWeight = FontWeight.Bold, color = Color(0xFF475569), modifier = Modifier.padding(bottom = 8.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Pembuatan Aplikasi Pengacakan Tim PAD", fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A), fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Mitra", color = Color.Gray, fontSize = 12.sp)
                    Text("PT. Pencari cinta sejati", fontWeight = FontWeight.Bold, color = Color(0xFF1E3A8A))
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Project Owner", color = Color.Gray, fontSize = 12.sp)
                    Text("Ir. Dinar Nugroho Pratomo, S.Kom., M.IM., M.Cs.", color = Color(0xFF1976D2))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { navController.popBackStack("profil", false) },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Selesai", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun TeamMemberCard(role: String, name: String, nim: String) {
    Text(role, fontWeight = FontWeight.Bold, color = Color(0xFF475569), modifier = Modifier.padding(top = 12.dp, bottom = 4.dp))
    Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF6FF)), modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.size(40.dp).background(Color.White, CircleShape))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(name, color = Color(0xFF1E3A8A), fontWeight = FontWeight.Medium)
                Text(nim, color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Hasil Role & Tim")
@Composable
fun HasilRolePreview() {
    SIPADTheme {
        HasilRoleScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Detail Kelompok")
@Composable
fun DetailKelompokPreview() {
    SIPADTheme {
        DetailKelompokScreen(navController = rememberNavController())
    }
}
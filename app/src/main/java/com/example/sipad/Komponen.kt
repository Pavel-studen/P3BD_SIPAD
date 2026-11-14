package com.example.sipad

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SIPADTopAppBar(title: String, onBackClick: (() -> Unit)? = null) {
    TopAppBar(
        title = { Text(title, color = Color.White, fontWeight = FontWeight.SemiBold) },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1976D2))
    )
}

@Composable
fun SIPADBottomNavigation(navController: NavController, currentRoute: String?) {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            selected = currentRoute == "profil" || currentRoute == "skillset",
            onClick = { navController.navigate("profil") { launchSingleTop = true } },
            icon = { Text("Pilih Peran", fontWeight = if (currentRoute == "profil") FontWeight.Bold else FontWeight.Normal, color = if (currentRoute == "profil") Color(0xFF1976D2) else Color.Gray) }
        )
        NavigationBarItem(
            selected = currentRoute == "hasil_role" || currentRoute == "detail_kelompok",
            onClick = { navController.navigate("hasil_role") { launchSingleTop = true } },
            icon = { Text("Hasil Role", fontWeight = if (currentRoute == "hasil_role") FontWeight.Bold else FontWeight.Normal, color = if (currentRoute == "hasil_role") Color(0xFF1976D2) else Color.Gray) }
        )
        NavigationBarItem(
            selected = currentRoute == "pengaduan",
            onClick = { navController.navigate("pengaduan") { launchSingleTop = true } },
            icon = { Text("Pengaduan", fontWeight = if (currentRoute == "pengaduan") FontWeight.Bold else FontWeight.Normal, color = if (currentRoute == "pengaduan") Color(0xFF1976D2) else Color.Gray) }
        )
    }
}
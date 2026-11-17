package com.example.sipad

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SIPADTopAppBar(
    title: String,
    onBackClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = {
            Text(
                title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
        },
        navigationIcon = {
            if (onBackClick != null) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1976D2)
        )
    )
}

@Composable
fun SIPADBottomNavigation(
    navController: NavController,
    currentRoute: String?
) {

    NavigationBar(
        containerColor = Color.White
    ) {

        // Menu Dashboard
        NavigationBarItem(
            selected = currentRoute == "dashboard",
            onClick = {
                navController.navigate("dashboard") {
                    popUpTo("dashboard") {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Dashboard"
                )
            },
            label = {
                Text("Home")
            }
        )

        // Menu Pilih Peran
        NavigationBarItem(
            selected = currentRoute == "profil" ||
                    currentRoute == "skillset",
            onClick = {
                navController.navigate("profil") {
                    popUpTo("dashboard") {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Pilih Peran"
                )
            },
            label = {
                Text("Pilih Peran")
            }
        )

        // Menu Hasil Role
        NavigationBarItem(
            selected = currentRoute == "hasil_role" ||
                    currentRoute == "detail_kelompok",
            onClick = {
                navController.navigate("hasil_role") {
                    popUpTo("dashboard") {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    Icons.Default.Groups,
                    contentDescription = "Hasil Role"
                )
            },
            label = {
                Text("Hasil Role")
            }
        )

        // Menu Pengaduan
        NavigationBarItem(
            selected = currentRoute == "pengaduan",
            onClick = {
                navController.navigate("pengaduan") {
                    popUpTo("dashboard") {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },
            icon = {
                Icon(
                    Icons.Default.Email,
                    contentDescription = "Pengaduan"
                )
            },
            label = {
                Text("Pengaduan")
            }
        )
    }
}

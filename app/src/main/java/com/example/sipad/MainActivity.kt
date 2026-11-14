package com.example.sipad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sipad.ui.theme.SIPADTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SIPADTheme {
                SIPADApp()
            }
        }
    }
}

@Composable
fun SIPADApp(){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomNav = currentRoute in listOf("profil", "hasil_role", "pengaduan")

    Scaffold(
        bottomBar = {
            if (showBottomNav){
                SIPADBottomNavigation(navController = navController, currentRoute = currentRoute)
            }
        }
    ){ innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "dashboard",
            modifier = Modifier.padding(innerPadding)
        ){
            composable("dashboard"){ DashboardScreen(navController) }
            composable("profil"){ ProfilScreen(navController) }
            composable("skillset"){ SkillsetScreen(navController) }
            composable("hasil_role"){ HasilRoleScreen(navController) }
            composable("detail_kelompok"){ DetailKelompokScreen(navController) }
        }
    }
}

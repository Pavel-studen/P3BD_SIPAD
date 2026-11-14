package com.example.sipad

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
fun ProfilScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        SIPADTopAppBar(title = "Profil & Pilih Peran", onBackClick = { navController.popBackStack() })

        Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
            // Profile header
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(64.dp).background(Color.LightGray, CircleShape))
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text("Paul Efraim", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = Color(0xFF1E3A8A))
                    Text("Unggah Foto Profil", color = Color(0xFF3B82F6), fontSize = 14.sp)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Form Fields
            OutlinedTextField(value = "Paul Efraim", onValueChange = {}, label = { Text("Nama") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = "paulefraims@mail.ugm.ac.id", onValueChange = {}, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = "25", onValueChange = {}, label = { Text("Angkatan") }, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = "UI/UX", onValueChange = {}, label = { Text("Role utama") }, modifier = Modifier.fillMaxWidth(), trailingIcon = { Icon(Icons.Default.ArrowDropDown, "") })
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = "Mobile", onValueChange = {}, label = { Text("Role kedua") }, modifier = Modifier.fillMaxWidth(), trailingIcon = { Icon(Icons.Default.ArrowDropDown, "") })
            Spacer(modifier = Modifier.height(12.dp))
            OutlinedTextField(value = "Portofolio.pdf", onValueChange = {}, label = { Text("Portofolio") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { navController.navigate("skillset") },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Selanjutnya", fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun SkillsetScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        SIPADTopAppBar(title = "Skillset Form", onBackClick = { navController.popBackStack() })

        Column(modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState())) {
            Text("Wih! Kerenn kamu pilih UI/UX sebagai role pilihan kamu!", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF1E3A8A), textAlign = androidx.compose.ui.text.style.TextAlign.Center, modifier = Modifier.fillMaxWidth())
            Spacer(modifier = Modifier.height(24.dp))

            SkillsetGroup("Tools Design yang akan digunakan?", listOf("Figma", "Adobe XD", "ProtoPie / Framer"))
            SkillsetGroup("UI Design skills yang dikuasai?", listOf("Design System & Component Library", "Responsive Grid & Auto-layout", "Typography & Color Hierarchy", "Micro-interactions"))
            SkillsetGroup("UX & Research skills yang dikuasai?", listOf("Wireframing & IA", "User Research & Interview", "Usability Testing (UT)", "User Journey Mapping / Persona"))
            SkillsetGroup("Collaboration & Feasibility skills?", listOf("Design Handoff (Dev Mode/Zeplin)", "Pemahaman Dasar HTML/CSS"))

            Row(modifier = Modifier.fillMaxWidth().padding(top = 24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Button(onClick = { navController.navigate("hasil_role") }, modifier = Modifier.weight(1f).height(50.dp), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)), shape = RoundedCornerShape(8.dp)) {
                    Text("Simpan", fontSize = 16.sp)
                }
                TextButton(onClick = { /* Clear logic */ }) {
                    Text("Clear", color = Color(0xFF1976D2))
                }
            }
        }
    }
}

@Composable
fun SkillsetGroup(title: String, items: List<String>) {
    Text(title, fontWeight = FontWeight.Bold, color = Color(0xFF475569), modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
    Card(colors = CardDefaults.cardColors(containerColor = Color.White), border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFF93C5FD))) {
        Column(modifier = Modifier.padding(8.dp)) {
            items.forEach { item ->
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Checkbox(checked = false, onCheckedChange = {})
                    Text(item, color = Color.Gray, fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Profil & Pilih Peran")
@Composable
fun ProfilPreview() {
    SIPADTheme {
        ProfilScreen(navController = rememberNavController())
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Skillset Form")
@Composable
fun SkillsetPreview() {
    SIPADTheme {
        SkillsetScreen(navController = rememberNavController())
    }
}
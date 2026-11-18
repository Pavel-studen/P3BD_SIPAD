package com.example.sipad

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material.icons.filled.UnfoldMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

private val warnaBiru = Color(0xFF0875DF)
private val warnaTeks = Color(0xFF4B6689)

private val pilihanRole = listOf(
    "Project Manager",
    "Backend Developer",
    "Frontend Developer",
    "UI/UX Designer"
)

@Composable
fun ProfilScreen(
    navController: NavController,
    mahasiswaViewModel: MahasiswaViewModel
) {
    val context = LocalContext.current
    val nimMahasiswa = "565845"

    val mahasiswa by mahasiswaViewModel
        .getMahasiswaByNim(nimMahasiswa)
        .collectAsState(initial = null)

    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var angkatan by remember { mutableStateOf("") }
    var roleUtama by remember { mutableStateOf("") }
    var roleKedua by remember { mutableStateOf("") }

    var portfolioPath by remember { mutableStateOf<String?>(null) }
    var fotoPath by remember { mutableStateOf<String?>(null) }

    var dataSudahDimuat by remember { mutableStateOf(false) }
    var pesanError by remember { mutableStateOf("") }

    val sudahSubmit = mahasiswa?.hasSubmitted == true

    LaunchedEffect(mahasiswa) {
        if (mahasiswa != null && !dataSudahDimuat) {
            nama = mahasiswa!!.nama
            email = mahasiswa!!.email
            angkatan = mahasiswa!!.angkatan
            roleUtama = mahasiswa!!.roleUtama
            roleKedua = mahasiswa!!.roleKedua
            portfolioPath = mahasiswa!!.portfolioUri
            fotoPath = mahasiswa!!.fotoProfilUri
            dataSudahDimuat = true
        }
    }

    // Pemilih foto profil
    val pemilihFoto = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            try {
                val pathBaru = salinFileKeInternal(
                    context = context,
                    uri = uri,
                    namaTujuan = "foto_${nimMahasiswa}"
                )

                fotoPath = pathBaru

                mahasiswa?.let { dataMahasiswa ->
                    mahasiswaViewModel.updateMahasiswa(
                        dataMahasiswa.copy(
                            fotoProfilUri = pathBaru
                        )
                    )
                }

                pesanError = ""
            } catch (e: Exception) {
                pesanError = "Gagal menyimpan foto profil."
            }
        }
    }

    // Pemilih dokumen PDF
    val pemilihPDF = rememberLauncherForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        if (uri != null) {
            try {
                val namaFile = getNamaFile(context, uri)

                if (!namaFile.endsWith(".pdf", ignoreCase = true)) {
                    pesanError = "Portofolio harus berupa file PDF."
                } else {
                    val pathBaru = salinFileKeInternal(
                        context = context,
                        uri = uri,
                        namaTujuan = "portofolio_${nimMahasiswa}.pdf"
                    )

                    portfolioPath = pathBaru

                    mahasiswa?.let { dataMahasiswa ->
                        mahasiswaViewModel.updateMahasiswa(
                            dataMahasiswa.copy(
                                portfolioUri = pathBaru
                            )
                        )
                    }

                    pesanError = ""
                }
            } catch (e: Exception) {
                pesanError = "Gagal menyimpan portofolio."
            }
        }
    }

    if (mahasiswa == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = warnaBiru)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // HEADER
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(76.dp)
                .background(warnaBiru)
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Kembali",
                    tint = Color.White
                )
            }

            Text(
                text = "Profil & Pilih Peran",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Normal
            )
        }

        // KONTEN FORM
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp)
                .padding(top = 28.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // FOTO DAN IDENTITAS
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                val bitmap = remember(fotoPath) {
                    fotoPath?.let {
                        BitmapFactory.decodeFile(it)?.asImageBitmap()
                    }
                }

                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFEAF3FC))
                        .clickable(enabled = !sudahSubmit) {
                            pemilihFoto.launch(arrayOf("image/*"))
                        },
                    contentAlignment = Alignment.Center
                ) {
                    if (bitmap != null) {
                        Image(
                            bitmap = bitmap,
                            contentDescription = "Foto profil",
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Foto profil",
                            tint = warnaBiru,
                            modifier = Modifier.size(52.dp)
                        )
                    }
                }

                Column {
                    Text(
                        text = nama.ifBlank { "Nama Mahasiswa" },
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = warnaTeks
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Upload Your Best Look!",
                        fontSize = 17.sp,
                        color = warnaBiru,
                        modifier = Modifier.clickable(
                            enabled = !sudahSubmit
                        ) {
                            pemilihFoto.launch(arrayOf("image/*"))
                        }
                    )
                }
            }

            // NAMA
            KolomInputProfil(
                label = "Nama",
                value = nama,
                onValueChange = { nama = it },
                enabled = !sudahSubmit
            )

            // EMAIL
            KolomInputProfil(
                label = "Email",
                value = email,
                onValueChange = { email = it },
                enabled = !sudahSubmit
            )

            // ANGKATAN
            KolomInputProfil(
                label = "Angkatan",
                value = angkatan,
                onValueChange = { angkatan = it },
                enabled = !sudahSubmit
            )

            // ROLE UTAMA
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                LabelProfil("Role utama")

                DropdownRoleProfil(
                    selectedRole = roleUtama,
                    options = pilihanRole,
                    enabled = !sudahSubmit,
                    onRoleSelected = {
                        roleUtama = it
                        if (roleKedua == it) {
                            roleKedua = ""
                        }
                    }
                )
            }

            // ROLE KEDUA
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                LabelProfil("Role kedua")

                DropdownRoleProfil(
                    selectedRole = roleKedua,
                    options = pilihanRole.filter { it != roleUtama },
                    enabled = !sudahSubmit,
                    onRoleSelected = { roleKedua = it }
                )
            }

            // PORTOFOLIO
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                LabelProfil("Portofolio")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color.White)
                        .clickable(enabled = !sudahSubmit) {
                            pemilihPDF.launch(arrayOf("application/pdf"))
                        }
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = portfolioPath?.let {
                            File(it).name
                        } ?: "Upload Portofolio PDF",
                        fontSize = 17.sp,
                        color = warnaTeks
                    )

                    Icon(
                        imageVector = Icons.Default.Upload,
                        contentDescription = "Upload PDF",
                        tint = Color.White,
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(warnaBiru)
                            .padding(6.dp)
                    )
                }

                // Border portofolio
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(warnaBiru)
                )
            }

            if (pesanError.isNotBlank()) {
                Text(
                    text = pesanError,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 14.sp
                )
            }

            if (sudahSubmit) {
                Text(
                    text = "Profil sudah dikirim dan tidak dapat diubah.",
                    color = warnaBiru
                )
            }

            // TOMBOL SELANJUTNYA
            Button(
                onClick = {
                    when {
                        nama.isBlank() ->
                            pesanError = "Nama tidak boleh kosong."

                        email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS
                            .matcher(email).matches() ->
                            pesanError = "Masukkan email yang valid."

                        angkatan.isBlank() ->
                            pesanError = "Angkatan tidak boleh kosong."

                        roleUtama.isBlank() ->
                            pesanError = "Pilih Role Utama."

                        roleKedua.isBlank() ->
                            pesanError = "Pilih Role Kedua."

                        roleUtama == roleKedua ->
                            pesanError = "Role utama dan kedua harus berbeda."

                        portfolioPath == null ->
                            pesanError = "Unggah portofolio PDF terlebih dahulu."

                        else -> {
                            mahasiswaViewModel.updateMahasiswa(
                                mahasiswa!!.copy(
                                    nama = nama,
                                    email = email,
                                    angkatan = angkatan,
                                    roleUtama = roleUtama,
                                    roleKedua = roleKedua,
                                    portfolioUri = portfolioPath,
                                    fotoProfilUri = fotoPath
                                )
                            )

                            pesanError = ""
                            navController.navigate("skillset")
                        }
                    }
                },
                enabled = !sudahSubmit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = warnaBiru
                )
            ) {
                Text(
                    text = "Selanjutnya",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun LabelProfil(label: String) {
    Text(
        text = label,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        color = warnaTeks
    )
}

@Composable
private fun KolomInputProfil(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        LabelProfil(label)

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(14.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = warnaBiru,
                unfocusedBorderColor = warnaBiru,
                focusedTextColor = warnaTeks,
                unfocusedTextColor = warnaTeks
            )
        )
    }
}

@Composable
private fun DropdownRoleProfil(
    selectedRole: String,
    options: List<String>,
    enabled: Boolean,
    onRoleSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedButton(
            onClick = { expanded = true },
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(14.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = warnaTeks
            )
        ) {
            Text(
                text = selectedRole.ifBlank { "Pilih peran" },
                fontSize = 17.sp,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.UnfoldMore,
                contentDescription = "Pilih role"
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            options.forEach { role ->
                DropdownMenuItem(
                    text = { Text(role) },
                    onClick = {
                        onRoleSelected(role)
                        expanded = false
                    }
                )
            }
        }
    }
}

private fun salinFileKeInternal(
    context: Context,
    uri: Uri,
    namaTujuan: String
): String {
    val namaFile = getNamaFile(context, uri)

    val ekstensi = if (namaTujuan.endsWith(".pdf", ignoreCase = true)) {
        "pdf"
    } else {
        namaFile.substringAfterLast(".", "jpg")
    }

    val namaDasar = namaTujuan.substringBeforeLast(".")

    val namaUnik = "${namaDasar}_${UUID.randomUUID()}.$ekstensi"

    val fileTujuan = File(context.filesDir, namaUnik)

    context.contentResolver.openInputStream(uri).use { input ->
        requireNotNull(input) {
            "File tidak dapat dibaca."
        }

        FileOutputStream(fileTujuan).use { output ->
            input.copyTo(output)
        }
    }

    return fileTujuan.absolutePath
}

private fun getNamaFile(
    context: Context,
    uri: Uri
): String {
    var namaFile = "file.pdf"

    context.contentResolver.query(
        uri,
        null,
        null,
        null,
        null
    )?.use { cursor ->
        val index = cursor.getColumnIndex(
            OpenableColumns.DISPLAY_NAME
        )

        if (cursor.moveToFirst() && index >= 0) {
            namaFile = cursor.getString(index)
        }
    }

    return namaFile
}

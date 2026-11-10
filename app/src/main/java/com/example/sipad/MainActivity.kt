package com.example.sipad

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sipad.ui.theme.SIPADTheme
import com.example.sipad.ui.theme.myBlue
import com.example.sipad.ui.theme.myGray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SIPADTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    Dashboard()
                }
            }
        }
    }
}

@Composable
fun Dashboard(){
    Column(Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .background(color = Color.White),
        horizontalAlignment = Alignment.Start
    ) {
        ConstraintLayout {
            val (blueBorder) = createRefs()
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .constrainAs(blueBorder) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .background(color = myBlue)
            )
            Row(modifier = Modifier
                .padding(top = 6.dp, start = 10.dp, end = 24.dp)
                .fillMaxWidth()
            ) {
                Column(modifier = Modifier
                    .height(54.dp)
                    .padding(start = 8.dp)
                    .weight(0.7f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(text = "SIPAD",
                        color = Color.White,
                        fontSize = 22.sp)
                    Text(text = "Sistem Informasi PAD",
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(bottom = 2.dp))
                }
            }
        }
        Text(text = "Hai! Paul Efraim,",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 20.dp, start = 24.dp))
        Text(text = "Hari ini mau ngapain?",
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 4.dp, start = 24.dp))

        ConstraintLayout(modifier = Modifier.fillMaxWidth()
            .padding(top = 18.dp, end = 24.dp, start = 24.dp)
            .shadow(1.dp, shape = RoundedCornerShape(10.dp))
            .height(100.dp)
            .background(color = myGray)
        ) {
            val (groupText, messageText) = createRefs()
            Text(text = "Kelompok 5",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = myBlue,
                modifier = Modifier.padding(top = 12.dp)
                    .constrainAs(groupText){
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    })
            Text(text = "Selamat! Teman kelompok PAD kamu udah ada nih!",
                fontSize = 14.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 6.dp, start = 18.dp, end = 18.dp)
                    .constrainAs(messageText){
                        top.linkTo(groupText.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    })
        }
        Text(text = "Project Manager",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 12.dp, start = 24.dp))

        ConstraintLayout(modifier = Modifier.fillMaxWidth()
            .padding(top = 4.dp, end = 24.dp, start = 24.dp)
            .shadow(1.dp, shape = RoundedCornerShape(10.dp))
            .height(64.dp)
            .background(color = myGray)
        ) {
            val (nameText, nimText) = createRefs()
            Text(text = "Caroline Emmanuelle",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                    .constrainAs(nameText){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })
            Text(text = "NIM 556977",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 16.dp)
                    .constrainAs(nimText){
                        top.linkTo(nameText.bottom)
                        start.linkTo(parent.start)
                    })
            }
        Text(text = "UI/UX",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 12.dp, start = 24.dp))

        ConstraintLayout(modifier = Modifier.fillMaxWidth()
            .padding(top = 4.dp, end = 24.dp, start = 24.dp)
            .shadow(1.dp, shape = RoundedCornerShape(10.dp))
            .height(64.dp)
            .background(color = myGray)
        ) {
            val (nameText, nimText) = createRefs()
            Text(
                text = "Michael Harold",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                    .constrainAs(nameText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })
            Text(
                text = "NIM 568209",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 16.dp)
                    .constrainAs(nimText) {
                        top.linkTo(nameText.bottom)
                        start.linkTo(parent.start)
                    })
        }
        Text(text = "Frontend",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 12.dp, start = 24.dp))

        ConstraintLayout(modifier = Modifier.fillMaxWidth()
            .padding(top = 4.dp, end = 24.dp, start = 24.dp)
            .shadow(1.dp, shape = RoundedCornerShape(10.dp))
            .height(64.dp)
            .background(color = myGray)
        ) {
            val (nameText, nimText) = createRefs()
            Text(
                text = "Paul Efraim",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                    .constrainAs(nameText) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })
            Text(
                text = "NIM 565845",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 16.dp)
                    .constrainAs(nimText) {
                        top.linkTo(nameText.bottom)
                        start.linkTo(parent.start)
                    })
        }
        Text(text = "Backend",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 12.dp, start = 24.dp))

        ConstraintLayout(modifier = Modifier.fillMaxWidth()
            .padding(top = 4.dp, end = 24.dp, start = 24.dp)
            .shadow(1.dp, shape = RoundedCornerShape(10.dp))
            .height(64.dp)
            .background(color = myGray)
        ) {
            val (nameText, nimText) = createRefs()
            Text(text = "Palava Ganesha",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                    .constrainAs(nameText){
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    })
            Text(text = "NIM 559909",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray,
                modifier = Modifier.padding(start = 16.dp)
                    .constrainAs(nimText){
                        top.linkTo(nameText.bottom)
                        start.linkTo(parent.start)
                    })
        }
        val context = LocalContext.current.applicationContext
        Button(onClick = { Toast.makeText(context, "Halaman profil sedang progress", Toast.LENGTH_SHORT).show() },
            shape = RoundedCornerShape(size = 10.dp), colors = ButtonDefaults.buttonColors(containerColor = myBlue),
            modifier = Modifier.fillMaxWidth()
                .padding(top = 18.dp, end = 24.dp, start = 24.dp)
        ) {
            Text(text = "Pilih Peran",
                fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DisplayDashboard(){
    SIPADTheme {
        Dashboard()
    }
}
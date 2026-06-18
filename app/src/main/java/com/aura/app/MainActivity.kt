package com.aura.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF050505)) {
                Column(Modifier.padding(24.dp)) {
                    Text("Aura Super App", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(24.dp))
                    Card(Modifier.fillMaxWidth().height(150.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.05f))) {
                        Column(Modifier.padding(24.dp)) {
                            Text("VAULT BALANCE", color = Color.Gray, fontSize = 12.sp)
                            Text("$42,500.00", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Black)
                        }
                    }
                    Spacer(Modifier.height(24.dp))
                    Button(onClick = {}, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00F5FF))) {
                        Text("SKYHIGH TRAVEL ACTIVE", color = Color.Black)
                    }
                }
            }
        }
    }
}

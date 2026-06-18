package com.aura.app
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val SkyHighAccent = Color(0xFF00F5FF)
val DeepBlack = Color(0xFF050505)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AuraApp() }
    }
}

@Composable
fun AuraApp() {
    var currentScreen by remember { mutableStateOf("home") }
    Box(modifier = Modifier.fillMaxSize().background(DeepBlack)) {
        AnimatedContent(targetState = currentScreen, label = "") { screen ->
            if (screen == "home") NexusHomeScreen { currentScreen = "skyhigh" }
            else SkyHighResultsScreen { currentScreen = "home" }
        }
    }
}

@Composable
fun NexusHomeScreen(onNav: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("Welcome, Anitha", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(24.dp))
        Surface(Modifier.fillMaxWidth().height(150.dp), color = Color.White.copy(0.05f), shape = RoundedCornerShape(24.dp)) {
            Column(Modifier.padding(24.dp)) {
                Text("VAULT BALANCE", color = Color.Gray, fontSize = 12.sp)
                Text("$42,500.00", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Black)
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = onNav, Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(containerColor = SkyHighAccent)) {
            Text("OPEN SKYHIGH TRAVEL", color = Color.Black)
        }
    }
}

@Composable
fun SkyHighResultsScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        IconButton(onClick = onBack) { Icon(Icons.Rounded.ArrowBack, null, tint = Color.White) }
        Text("TOKYO FLIGHTS", Modifier.padding(24.dp), color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Black)
        LazyColumn { items(5) { 
            Card(Modifier.fillMaxWidth().padding(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(0.05f))) {
                Text("$842.00", Modifier.padding(24.dp), color = SkyHighAccent, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        } }
    }
}

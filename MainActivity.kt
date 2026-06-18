package com.example.aura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- AURA THEME ---
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
        AnimatedContent(
            targetState = currentScreen,
            transitionSpec = { fadeIn(tween(500)) with fadeOut(tween(500)) }
        ) { screen ->
            when (screen) {
                "home" -> NexusHomeScreen(onNavigate = { currentScreen = "skyhigh" })
                "skyhigh" -> SkyHighResultsScreen(onBack = { currentScreen = "home" })
            }
        }
    }
}

@Composable
fun NexusHomeScreen(onNavigate: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        // Top Header
        Row(Modifier.fillMaxWidth().padding(24.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Column {
                Text("Welcome back, Anitha", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Text("Vault Secured • 100%", color = SkyHighAccent, fontSize = 12.sp)
            }
            Icon(Icons.Rounded.Notifications, contentDescription = null, tint = Color.White)
        }

        // Vault Preview
        Surface(
            modifier = Modifier.fillMaxWidth().padding(24.dp).height(160.dp),
            color = Color.White.copy(alpha = 0.05f),
            shape = RoundedCornerShape(32.dp),
            border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
        ) {
            Column(Modifier.padding(24.dp)) {
                Text("VAULT BALANCE", color = Color.Gray, fontSize = 12.sp)
                Text("$42,500.00", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Black)
            }
        }

        // SkyHigh Entry
        Text("SKYHIGH SERVICES", color = Color.Gray, modifier = Modifier.padding(start = 24.dp))
        Surface(
            modifier = Modifier.fillMaxWidth().padding(24.dp).clickable { onNavigate() },
            color = Color.White.copy(alpha = 0.05f),
            shape = RoundedCornerShape(24.dp)
        ) {
            Row(Modifier.padding(24.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Rounded.FlightTakeoff, contentDescription = null, tint = SkyHighAccent)
                Spacer(Modifier.width(16.dp))
                Column {
                    Text("Search Flights", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("AI Sniper Active", color = Color.Gray, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun SkyHighResultsScreen(onBack: () -> Unit) {
    val scrollState = rememberLazyListState()
    Box(modifier = Modifier.fillMaxSize()) {
        // Parallax Content
        LazyColumn(state = scrollState) {
            item { 
                Box(Modifier.fillMaxWidth().height(300.dp).background(Brush.verticalGradient(listOf(Color.DarkGray, DeepBlack)))) {
                    Text("TOKYO", modifier = Modifier.align(Alignment.Center), fontSize = 64.sp, color = Color.White, fontWeight = FontWeight.Black)
                }
            }
            items(5) { 
                FlightCard()
            }
        }
        
        // Top Nav
        IconButton(onClick = onBack, modifier = Modifier.statusBarsPadding().padding(16.dp).background(Color.Black.copy(0.5f), CircleShape)) {
            Icon(Icons.Rounded.ArrowBack, contentDescription = null, tint = Color.White)
        }
    }
}

@Composable
fun FlightCard() {
    Surface(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        color = Color.White.copy(alpha = 0.05f),
        shape = RoundedCornerShape(24.dp),
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.1f))
    ) {
        Row(Modifier.padding(24.dp)) {
            Column(Modifier.weight(1f)) {
                Text("SKYHIGH OPTIMAL", color = SkyHighAccent, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                Text("$842", color = Color.White, fontSize = 28.sp, fontWeight = FontWeight.Black)
            }
            Icon(Icons.Rounded.Bolt, contentDescription = null, tint = SkyHighAccent)
        }
    }
}
